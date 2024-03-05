package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.controller.ControllableGameModel;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements ViewableGameModel, ControllableGameModel, ContactListener {
    private static final float GRAVITY = -9.81f;
    private static final float WIND = 0;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private final List<TileModel> foreground;
    private final List<TileModel> background;
    private final List<Item> items;
    private final World world;
    private final PlayerModel player;
    private GameState state;
    private PlayerModel playerModel;

    public GameModel() {
        foreground = new ArrayList<>();
        background = new ArrayList<>();
        items = new ArrayList<>();
        world = new World(new Vector2(WIND, GRAVITY), true);
        player = new PlayerModel(world, 1.5f, 6.5f);
        state = GameState.GAME_OVER;

        fillWorld();
    }

    /**
     * Used for testing
     */
    private void fillWorld() {
        List<TileModel> tiles = TileFactory.generate(
                """
                       -0--
                       ---0
                       0000
                       """,
                world,new EventBus());
        foreground.addAll(tiles);
    }

    @Override
    public void beginContact(Contact contact) {}

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold manifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {}

    @Override
    public ControllablePlayerModel getControllablePlayer() {
        return player;
    }

    @Override
    public void setState(GameState state) {
        this.state = state;
    }

    @Override
    public GameState getState() {
        return state;
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
}
