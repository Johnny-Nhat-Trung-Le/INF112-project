package inf112.lilBro.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import inf112.lilBro.app.controller.ControllablePlayerModel;
import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.event.EventDeath;
import inf112.lilBro.app.model.event.EventDispose;
import inf112.lilBro.app.model.event.EventItemPickedUp;
import inf112.lilBro.app.model.event.EventReachedDoor;
import inf112.lilBro.app.model.item.ItemModel;
import inf112.lilBro.app.model.tiles.TileModel;
import inf112.lilBro.app.model.tiles.contactableTiles.ContactableTiles;
import inf112.lilBro.app.view.ViewableItem;
import inf112.lilBro.app.view.ViewablePlayerModel;
import inf112.lilBro.app.view.ViewableTile;

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

    private List<ItemModel> toBeRemoved;
    private boolean isActive;
    private boolean initialized;

    /**
     * Creates a level that is disabled. Use {@linkplain Level}::activate to activate.
     *
     * @param bus         {@link EventBus} that {@link Event}s are posted in
     * @param void_height where the player dies if is below
     * @param gravity     vertical gravity (down is negative)
     * @param wind        horizontal gravity (left is negative)
     * @param player_x    left-most horizontal position
     * @param player_y    bottom-most vertical position
     * @param foreground  string to be used in {@link TileFactory}::generate
     * @param background  string to be used in {@link TileFactory}::generate
     * @param items       to be used to create the {@link TileModel}s
     */
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
        isActive = false;
        initialized = false;

        reset();
    }

    @Override
    public void beginContact(Contact contact) {
        player.beginContact(contact);

        for (TileModel tile : foreground) {
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
            toBeRemoved.add(e.item());
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

        toBeRemoved.forEach(ItemModel::destroyBody);
        toBeRemoved.forEach(bus::removeEventHandler);
        toBeRemoved.forEach((i) -> i.handleEvent(new EventDispose()));
        toBeRemoved.clear();
    }

    @Override
    public ControllablePlayerModel getControllablePlayer() {
        return player;
    }

    private void removeEventHandlers() {
        bus.removeEventHandler(player);
        foreground.forEach(bus::removeEventHandler);
        background.forEach(bus::removeEventHandler);
        items.forEach(bus::removeEventHandler);
    }

    private void addEventHandlers() {
        bus.addEventHandler(player);
        foreground.forEach(bus::addEventHandler);
        background.forEach(bus::addEventHandler);
        items.forEach(bus::addEventHandler);
    }

    private void disposeContents() {
        player.handleEvent(new EventDispose());
        foreground.forEach((t) -> t.handleEvent(new EventDispose()));
        background.forEach((t) -> t.handleEvent(new EventDispose()));
        items.forEach((i) -> i.handleEvent(new EventDispose()));
    }

    @Override
    public void reset() {
        if (initialized) {
            disposeContents();
            removeEventHandlers();
        } else initialized = true;

        this.world = new World(new Vector2(wind, gravity), true);
        this.world.setContactListener(this);
        this.player = new PlayerModel(bus, world, void_height, default_player_x, default_player_y);
        this.foreground = TileFactory.generate(default_foreground, world, bus);
        this.background = TileFactory.generate(default_background, new World(new Vector2(0, 0), true), bus);
        this.items = new CopyOnWriteArrayList<>(default_items.stream().map(e -> e.apply(world)).toList());

        this.toBeRemoved = new CopyOnWriteArrayList<>();

        if (!isActive) removeEventHandlers();
    }

    @Override
    public void activate() {
        if (isActive) return;
        bus.addEventHandler(this);
        addEventHandlers();
        isActive = true;
    }

    @Override
    public void disable() {
        bus.removeEventHandler(this);
        removeEventHandlers();
        isActive = false;
    }

    @Override
    public ViewablePlayerModel getViewablePlayer() {
        return player;
    }

    @Override
    public List<ViewableTile> getForegroundTiles() {
        return foreground.stream().map(e -> (ViewableTile) e).toList();
    }

    @Override
    public List<ViewableTile> getBackgroundTiles() {
        return background.stream().map(e -> (ViewableTile) e).toList();
    }

    @Override
    public List<ViewableItem> getItems() {
        return items.stream().map(e -> (ViewableItem) e).toList();
    }
}
