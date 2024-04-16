package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.EventDeath;
import inf112.skeleton.app.model.event.EventItemPickedUp;
import inf112.skeleton.app.model.event.EventReachedDoor;
import inf112.skeleton.app.model.item.ItemModel;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.model.tiles.contactableTiles.ContactableTiles;
import inf112.skeleton.app.utils.Function4;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class Level implements ILevel {
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 8;
    public final float void_height;
    private final EventBus bus;
    private final float gravity;
    private final float wind;

    private final String default_foreground;
    private final String default_background;
    private final List<Function<World, ItemModel>> default_items;
    private final float default_player_x;
    private final float default_player_y;

    private World world;
    private List<TileModel> foreground;
    private List<TileModel> background;
    private List<ItemModel> items;
    private PlayerModel player;

    public Level(
            EventBus bus,
            float void_height,
            float gravity,
            float wind,
            float player_x,
            float player_y,
            String foreground,
            String background,
            List<Function<World, ItemModel>> items
    ) {
        this.void_height = void_height;
        this.gravity = gravity;
        this.wind = wind;
        this.bus = bus;
        default_player_x = player_x;
        default_player_y = player_y;
        default_foreground = foreground;
        default_background = background;
        default_items = items;

        reset();
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
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventItemPickedUp e) {
            items.remove(e.item());
        } else if (event instanceof EventReachedDoor) {
            reset();
        } else if (event instanceof EventDeath e) {
            if (player.equals(e.owner())) {
                reset();
            }
        }
    }

    @Override
    public void step(float timeStep) {
        player.step(timeStep);
        world.step(timeStep, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    @Override
    public ControllablePlayerModel getControllablePlayer() {
        return player;
    }

    @Override
    public void reset() {
        this.world = new World(new Vector2(wind, gravity), true);
        this.player = new PlayerModel(bus, world, void_height, default_player_x, default_player_y);
        this.foreground = TileFactory.generate(default_foreground, world, bus);
        this.background = TileFactory.generate(default_background, world, bus);
        this.items = new CopyOnWriteArrayList<>(default_items.stream().map(e -> e.apply(world)).toList());
        world.setContactListener(this);
    }

    @Override
    public void activate() {
        bus.addEventHandler(this);
    }

    @Override
    public void disable() {
        bus.removeEventHandler(this);
    }

    @Override
    public ViewablePlayerModel getViewablePlayer() {
        return player;
    }

    @Override
    public Iterable<ViewableTile> getForegroundTiles() {
        return foreground.stream().map(e -> (ViewableTile) e).toList();
    }

    @Override
    public Iterable<ViewableTile> getBackgroundTiles() {
        return background.stream().map(e -> (ViewableTile) e).toList();
    }

    @Override
    public Iterable<ViewableItem> getItems() {
        return items.stream().map(e -> (ViewableItem) e).toList();
    }
}
