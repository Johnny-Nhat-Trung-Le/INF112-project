package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.PlayerModel;
import inf112.skeleton.app.model.event.EventDamage;
import inf112.skeleton.app.model.event.EventReachedDoor;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.utils.ContactListeners;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestEventTile {
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final int NUM_ITERATIONS = 60;
    private static final float DT = 1 / 60f;
    private static final float INIT_X = 0;
    private static final float INIT_Y = 30;
    private static final float GRAVITY_X = 0;
    private static final float GRAVITY_Y = 0;
    private static final float x = 10;
    private static final float width = TileModel.TILE_WIDTH;
    private static final float height = TileModel.TILE_HEIGHT;
    private World world;
    private PlayerModel player;
    private ContactListeners contactListeners;
    private EventBus bus;
    private List<Event> events;

    private void step() {
        player.step(DT);
        world.step(DT, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    @BeforeEach
    public void reset() {
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
        events = new CopyOnWriteArrayList<>();
        bus = new EventBus();
        bus.addEventHandler(this::handleEvent);
        player = new PlayerModel(bus, world, INIT_X, INIT_Y);
        contactListeners = new ContactListeners();
        contactListeners.add(player);
        world.setContactListener(contactListeners);
    }

    private void handleEvent(Event event) {
        events.add(event);
    }

    @Test
    public void testEventDamageSpike() {
        Spike spike = new Spike(world, bus, x, player.getY() + (player.getHeight() / 2), width, height);
        contactListeners.add(spike);
        player.moveRight(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        assertEquals(1, events.size(), "should have been posted an eventDamage");
        assertInstanceOf(EventDamage.class, events.get(0), "should have been an eventDamage");
    }

    @Test
    public void testEventDamageSaw() {
        Saw saw = new Saw(world, bus, player.getX() - player.getWidth(), player.getY() + width, width, height);
        contactListeners.add(saw);
        player.moveLeft(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();

        }
        assertEquals(1, events.size(), "should post 1 event");
        assertInstanceOf(EventDamage.class, events.get(0), "should be an event of type EventDamage");

    }

    @Test
    public void testEventDoor() {
        Door1 door = new Door1(world, bus, player.getX() + player.getWidth(), player.getY() + 4, width, height);
        contactListeners.add(door);

        player.moveRight(true);
        step();
        assertEquals(2, events.size(), "Should be an event that was posted");
        assertInstanceOf(EventReachedDoor.class, events.get(0), "Should be an event of reached door");
    }

    @Test
    public void testEventDamageDoubleSaw() {
        Saw saw = new Saw(world, bus, player.getX() - player.getWidth(), player.getY() + width, width, height);
        Saw saw1 = new Saw(world, bus, saw.getX() - saw.getWidth(), saw.getY() + width, width, height);
        contactListeners.add(saw);
        contactListeners.add(saw1);
        player.moveLeft(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        assertEquals(2, events.size(), "should post 2 event");
        assertInstanceOf(EventDamage.class, events.get(0), "should be an event of type EventDamage");
        assertEquals(events.get(0), events.get(1), "both events should be of type EventDamage");

    }

    @Test
    public void testEventDamageTrippelSpike() {
        Spike spike = new Spike(world, bus, player.getX() - player.getWidth(), player.getY() + (player.getHeight() / 2));
        Spike spike1 = new Spike(world, bus, spike.getX() - spike.getWidth(), spike.getY() + (player.getHeight() / 2));
        Spike spike2 = new Spike(world, bus, spike1.getX() - spike.getWidth(), spike.getY() + (player.getHeight() / 2));
        contactListeners.add(spike);
        contactListeners.add(spike1);
        contactListeners.add(spike2);
        player.moveLeft(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        assertEquals(3, events.size(), "should post 3 events");
        assertInstanceOf(EventDamage.class, events.get(0), "should be an event of type EventDamage");
        assertTrue(events.get(0).equals(events.get(1)) && events.get(1).equals(events.get(2)), "all events should be of type EventDamage");

    }

}
