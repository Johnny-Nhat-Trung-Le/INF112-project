package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.controller.ControllableGameModel;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.event.EventGameState;
import inf112.skeleton.app.model.event.EventItemPickedUp;
import inf112.skeleton.app.model.item.ItemModel;
import inf112.skeleton.app.model.item.ItemMushroom;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.model.tiles.contactableTiles.ContactableTiles;
import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameModel implements ViewableGameModel, ControllableGameModel, ContactListener, EventHandler {
    private static final float GRAVITY = -20;
    private static final float WIND = 0;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 8;
    private final EventBus bus;
    private final List<TileModel> foreground;
    private final List<TileModel> background;
    private String bruh = """
                        --B--------------------
                        qwe--------------------lgr
                        ------ssB----------w------
                        -----lggr-------------w---
                        LGR--gggg----------------i-----sss--S-S--S
                        GG---gggg---S------S-SS-B------LGGGGGGGGGR-----------w-------9
                        ----qg------lgr--lggggggggr----GGGGGGGGGGG---g--s-B----------8
                        GGG---------------------------------------------qwe-------LGgR
                        """;

    private final List<ItemModel> items;
    private final World world;
    private final PlayerModel player;
    private GameState state;
    private final AssetsManager assetsManager;

    public GameModel(EventBus bus) {
        this.bus = bus;
        foreground = new ArrayList<>();
        background = new ArrayList<>();
        items = new CopyOnWriteArrayList<>();
        world = new World(new Vector2(WIND, GRAVITY), true);
        player = new PlayerModel(bus, world, 15.5f, 22f);
        assetsManager = new AssetsManager();
        state = GameState.MAIN_MENU;

        bus.addEventHandler(this);
        world.setContactListener(this); // If more bodies need to be ContactListener
        fillWorld();
    }

    /**
     * Used for testing
     */
    private void fillWorld() {
        List<TileModel> tiles = TileFactory.generate("""
                        ---------------------------------------------------------9
                        ---------------------------------------------------------8
                        ----------------------------------------------LGGGGGGGGGGR
                        LGGGGGR--------------------------------------qGGGGGGGGGGGG
                        GGGGGGG------------------------------------B----------GGGG
                        GGGGGGG------------------------------------i----------GGGG
                        GGGGGGG----------------------------------------LR-----GGGG
                        GGGGGGG----------------------------------------GGGr---GGGG
                        GGGGGGG----|------lr---------------------------------BGGGG
                        GGGGGGGe-----LR------------------------------------GGGGGGG
                        GGGGGGG---G--GG-------G------------------------S-GGGGGGGGG
                        GGGGGGG--eG--GG-------G------------------s---LGGGGGGGGGGG
                        GGGGGGGB-----GG--------e-------------s---lgr--GGGGGGGGGGGG
                        GGGGGGGwe----GG---------------SS----lgr-------------------
                        GGG-------|--GG-----------I--lgggr------------------------
                        GGG---------BGG-----------G-------------------------------
                        GGG--------qwGGSSSSSSSSS--G-------------------------------
                        GGGGGGGGR----GGGGGGGGGGR--G-------------------------------
                        GGGGGGGGG----GGGGGGGGGGG--G-------------------------------
                        GGGGGGGGG----GGGGGGGGGGG--G-------------------------------
                        GGGGGGGGG----GGGGGGGGGGG----------------------------------
                        """,
                
                world, bus);
        foreground.addAll(tiles);
        //items.add(new ItemEnergy(bus, world, 15, 7));
        //items.add(new ItemMushroom(bus, world, 18f, 20f));
    }

    @Override
    public void beginContact(Contact contact) {
        player.beginContact(contact);
        for (TileModel tile : foreground.stream().toList()) {
            if (tile instanceof ContactableTiles) {
                ((ContactableTiles) tile).beginContact(contact);
            }
        }
        for (ItemModel item : items) {
            item.beginContact(contact);
        }
    }

    @Override
    public void endContact(Contact contact) {
        player.endContact(contact);
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        player.preSolve(contact, manifold);
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
        player.postSolve(contact, contactImpulse);
    }

    @Override
    public ControllablePlayerModel getControllablePlayer() {
        return player;
    }

    @Override
    public AssetsManager getAssetsManager() {
        return this.assetsManager;
    }

    @Override
    public GameState getState() {
        return state;
    }

    @Override
    public void setState(GameState state) {
        if (state == GameState.ACTIVE && this.state != GameState.ACTIVE) {
            if (this.state == GameState.MAIN_MENU) {
                assetsManager.stopMusic();
            }
            if (this.state == GameState.PAUSE) {
                assetsManager.resumeMusic();
            } else {
                assetsManager.playMusic("BACKGROUND");
            }
        } else if (this.state == GameState.ACTIVE && state != GameState.ACTIVE) {
            assetsManager.pauseMusic();
        }
        this.state = state;
    }

    @Override
    public ViewablePlayerModel getViewablePlayer() {
        return player;
    }

    @Override
    public Iterable<ViewableTile> getForegroundTiles() {
        return foreground.stream().map((t) -> (ViewableTile) t).toList();
    }

    @Override
    public Iterable<ViewableTile> getBackgroundTiles() {
        return background.stream().map((t) -> (ViewableTile) t).toList();
    }

    @Override
    public Iterable<ViewableItem> getItems() {
        return items.stream().map((i) -> (ViewableItem) i).toList();
    }

    @Override
    public void step(float timeStep) {
        player.step(timeStep);
        world.step(timeStep, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventItemPickedUp e) {
            items.remove(e.item());
        } else if (event instanceof EventGameState e) {
            GameState gamestate = e.gameState();
            if (gamestate.equals(GameState.VICTORY)) {
                System.out.println("VICTORY ROYALE");
            } else if (gamestate.equals(GameState.GAME_OVER)) {
                state = GameState.GAME_OVER;
            }
        }
    }
}
