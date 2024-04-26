package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.*;
import inf112.skeleton.app.model.item.ItemEnergy;
import inf112.skeleton.app.model.item.ItemModel;
import inf112.skeleton.app.model.tiles.TileGround;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.model.tiles.contactableTiles.Door1;
import inf112.skeleton.app.model.tiles.contactableTiles.Spike;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestLevel {
    private static final int ITERATIONS = 500;
    private static final float TIME_STEP = 1 / (float) 60;
    private EventBus bus;
    private Level level;
    private List<Event> events;

    @BeforeAll
    public static void beforeAll() {
        TileFactory.register('G', TileGround::new);
        TileFactory.register('9', Door1::new);
        TileFactory.register('s', Spike::new);
    }

    private void handleEvent(Event e) {
        events.add(e);
    }

    private Level createLevel(float gravity) {
        return createLevel(gravity, "----G\nGGG9", TileModel.TILE_HEIGHT);
    }

    private Level createLevel(String foreground, float player_y) {
        return createLevel(0, foreground, player_y);
    }

    private Level createLevel(float gravity, String foreground) {
        return createLevel(gravity, foreground, TileModel.TILE_HEIGHT);
    }

    private Level createLevel(float gravity, String foreground, float player_y) {
        List<Function<World, ItemModel>> items = new ArrayList<>();
        items.add((World world) -> new ItemEnergy(bus, world, TileModel.TILE_WIDTH * 4, TileModel.TILE_HEIGHT));

        return new Level(
                bus,
                -20,
                gravity, 0,
                0, player_y,
                foreground,
                """
                        GGG
                        ggg
                        g
                        """,
                items);
    }

    @BeforeEach
    public void beforeEach() {
        events = new ArrayList<>();
        bus = new EventBus();
        bus.addEventHandler(this::handleEvent);

        level = createLevel(0);
        level.activate();
    }

    @Test
    public void testReset() {
        List<ViewableTile> background = new ArrayList<>(level.getBackgroundTiles());
        List<ViewableTile> foreground = new ArrayList<>(level.getForegroundTiles());
        List<ViewableItem> items = new ArrayList<>(level.getItems());
        ViewablePlayerModel player = level.getViewablePlayer();

        level.reset();

        // BACKGROUND
        assertEquals(background.size(), level.getBackgroundTiles().size(), "The level's background is not the same length after Level::reset!");
        for (int i = 0; i < background.size(); i++) {
            ViewableTile old = background.get(i);
            ViewableTile tile = level.getBackgroundTiles().get(i);

            assertEquals(old.getX(), tile.getX(), "A tile has the wrong x-position after Level::reset!");
            assertEquals(old.getY(), tile.getY(), "A tile has the wrong y-position after Level::reset!");
            assertEquals(old.getWidth(), tile.getWidth(), "A tile has the wrong width after Level::reset!");
            assertEquals(old.getHeight(), tile.getHeight(), "A tile has the wrong height after Level::reset!");
        }

        // FOREGROUND
        assertEquals(foreground.size(), level.getForegroundTiles().size(), "The level's foreground is not the same length after Level::reset!");
        for (int i = 0; i < foreground.size(); i++) {
            ViewableTile old = foreground.get(i);
            ViewableTile tile = level.getForegroundTiles().get(i);

            assertEquals(old.getX(), tile.getX(), "A tile has the wrong x-position after Level::reset!");
            assertEquals(old.getY(), tile.getY(), "A tile has the wrong y-position after Level::reset!");
            assertEquals(old.getWidth(), tile.getWidth(), "A tile has the wrong width after Level::reset!");
            assertEquals(old.getHeight(), tile.getHeight(), "A tile has the wrong height after Level::reset!");
        }

        // ITEMS
        assertEquals(items.size(), level.getItems().size(), "The level's items is not the same length after Level::reset!");
        for (int i = 0; i < items.size(); i++) {
            ViewableItem old = items.get(i);
            ViewableItem item = level.getItems().get(i);

            assertEquals(old.getX(), item.getX(), "An item has the wrong x-position after Level::reset!");
            assertEquals(old.getY(), item.getY(), "An item has the wrong y-position after Level::reset!");
            assertEquals(old.getWidth(), item.getWidth(), "An item has the wrong width after Level::reset!");
            assertEquals(old.getHeight(), item.getHeight(), "An item has the wrong height after Level::reset!");
        }

        // PLAYER
        assertEquals(player.getX(), level.getViewablePlayer().getX(), "An item has the wrong x-position after Level::reset!");
        assertEquals(player.getY(), level.getViewablePlayer().getY(), "An item has the wrong y-position after Level::reset!");
        assertEquals(player.getWidth(), level.getViewablePlayer().getWidth(), "An item has the wrong width after Level::reset!");
        assertEquals(player.getHeight(), level.getViewablePlayer().getHeight(), "An item has the wrong height after Level::reset!");
    }

    @Test
    public void testItemPickupWithReset() {
        int numItems = level.getItems().size();

        level.getControllablePlayer().moveRight(true);
        for (int i = 0; i < ITERATIONS; i++) {
            level.step(TIME_STEP);
        }

        assertEquals(2, events.size(), "More than two events was posted");
        assertInstanceOf(EventItemContact.class, events.get(0), "The first event posted was not: " + EventItemContact.class);
        assertInstanceOf(EventItemPickedUp.class, events.get(1), "The second event posted was not: " + EventItemPickedUp.class);

        assertEquals(numItems - 1, level.getItems().size(), "The item was not removed from the items-list");

        level.reset();
        events.clear();

        numItems = level.getItems().size();

        level.getControllablePlayer().moveRight(true);
        for (int i = 0; i < ITERATIONS; i++) {
            level.step(TIME_STEP);
        }

        assertEquals(2, events.size(), "More than two events was posted");
        assertInstanceOf(EventItemContact.class, events.get(0), "The first event posted was not: " + EventItemContact.class);
        assertInstanceOf(EventItemPickedUp.class, events.get(1), "The second event posted was not: " + EventItemPickedUp.class);

        assertEquals(numItems - 1, level.getItems().size(), "The item was not removed from the items-list");
    }

    @Test
    public void testActivateAndDisable() {
        if (level.getItems().isEmpty()) return;
        ItemModel item = (ItemModel) level.getItems().get(0);

        level.disable();
        int numItems = level.getItems().size();
        bus.post(new EventItemPickedUp(item));
        assertEquals(numItems, level.getItems().size(), "The item-list was changed when ´EventItemPickedUp´ was called while the level was disabled!");

        level.activate();
        bus.post(new EventItemPickedUp(item));
        level.step(1);
        assertEquals(numItems - 1, level.getItems().size(), "The item-list was not changed when ´EventItemPickedUp´ was called while the level was active!");
    }

    @Test
    public void testPlayerVoid() {
        level = createLevel(-20, "");
        level.activate();

        // TEST DEATH
        for (int i = 0; i < ITERATIONS; i++) {
            level.step(TIME_STEP);

            // exit loop after first death
            if (!events.isEmpty()) break;
        }

        assertEquals(1, events.size(), "More than one event was posted on player void");
        assertInstanceOf(EventDeath.class, events.get(0), "The event posted on player void was not " + EventDeath.class);

        // RESET
        level.reset();
        events.clear();

        // TEST DEATH AFTER RESET
        for (int i = 0; i < ITERATIONS; i++) {
            level.step(TIME_STEP);

            // exit loop after first death
            if (!events.isEmpty()) break;
        }

        assertEquals(1, events.size(), "More than one event was posted on player void");
        assertInstanceOf(EventDeath.class, events.get(0), "The event posted on player void was not " + EventDeath.class);

    }

    @Test
    public void testPlayerReachedDoor() {
        level.disable();
        level = createLevel("-9\n-", TileModel.TILE_HEIGHT - 1);
        level.activate();

        // TEST REACHED DOOR
        level.getControllablePlayer().moveRight(true);
        for (int i = 0; i < ITERATIONS; i++) {
            level.step(TIME_STEP);
            if (!events.isEmpty()) break;
        }

        assertEquals(1, events.size(), "There were not posted exactly one event on player reached door!");
        assertInstanceOf(EventReachedDoor.class, events.get(0), "The event on player reached door was not " + EventReachedDoor.class);

        // RESET
        level.reset();
        events.clear();

        // TEST REACHED DOOR AFTER RESET
        level.getControllablePlayer().moveRight(true);
        for (int i = 0; i < ITERATIONS; i++) {
            level.step(TIME_STEP);
            if (!events.isEmpty()) break;
        }

        assertEquals(1, events.size(), "There were not posted exactly one event on player reached door!");
        assertInstanceOf(EventReachedDoor.class, events.get(0), "The event on player reached door was not " + EventReachedDoor.class);
    }

    @Test
    public void testPlayerSpiked() {
        level.disable();
        level = createLevel("--s\n-", TileModel.TILE_HEIGHT - 1);
        level.activate();

        // TEST SPIKE TOUCH
        level.getControllablePlayer().moveRight(true);
        for (int i = 0; i < ITERATIONS; i++) {
            level.step(TIME_STEP);
        }

        assertEquals(1, events.size(), "More than one event was posted after player hit spike!");
        assertInstanceOf(EventDamage.class, events.get(0), "Event after player hit spike was not " + EventDamage.class);

        // RESET
        level.reset();
        events.clear();

        // TEST SPIKE TOUCH AFTER RESET
        level.getControllablePlayer().moveRight(true);
        for (int i = 0; i < ITERATIONS; i++) {
            level.step(TIME_STEP);
        }

        assertEquals(1, events.size(), "More than one event was posted after player hit spike!");
        assertInstanceOf(EventDamage.class, events.get(0), "Event after player hit spike was not " + EventDamage.class);
    }

    @Test
    public void testActivateDisable() {
        // MUST-HAVE ITEM FOR TEST
        if (level.getItems().isEmpty()) return;

        // DISABLED
        level.disable();

        int numItems = level.getItems().size();
        bus.post(new EventItemPickedUp((ItemModel) level.getItems().get(0)));
        assertEquals(numItems, level.getItems().size(), "The item-list was changed when the level was disabled!");

        // DOUBLE ACTIVATED
        level.activate();
        level.activate();

        bus.post(new EventItemPickedUp((ItemModel) level.getItems().get(0)));
        assertEquals(numItems - 1, level.getItems().size(), "The item did not get removed after " + EventItemPickedUp.class + " was posted in active level!");
    }

}
