package inf112.lilBro.app.model.tiles.contactableTiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.event.EventDamage;
import inf112.lilBro.app.utils.ContactListeners;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestSaw {
    private static final int ITERATIONS = 600;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final float TIME_STEP = 1 / (float) 60;
    private static final float INIT_X = 10;
    private static final float INIT_Y = 20;
    private static final float DIAMETER = 5;
    private static final float HEIGHT = 5;

    private EventBus bus;
    private World world;
    private Saw saw;
    private List<Event> events;
    private ContactListeners contactListeners;

    private void handleEvent(Event e) {
        events.add(e);
    }

    private void step() {
        world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    /**
     * @param x left-most
     * @param y bottom-most
     * @param width full
     * @param height full
     */
    private Body createBody(float x, float y, float width, float height) {
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(x + width / 2, y + height / 2);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 1;
        fd.shape = shape;

        Body b = world.createBody(bd);
        Fixture f = b.createFixture(fd);

        return b;
    }

    private Saw createSaw(float offsetX, float offsetY) {
        return new Saw(world, bus, INIT_X + DIAMETER / 2 + offsetX, INIT_Y + HEIGHT / 2 + offsetY, DIAMETER);
    }

    @BeforeEach
    public void beforeEach() {
        bus = new EventBus();
        world = new World(new Vector2(0, 0), true);
        saw = createSaw(0, 0);
        events = new ArrayList<>();
        contactListeners = new ContactListeners();

        contactListeners.add(saw);
        world.setContactListener(contactListeners);
        bus.addEventHandler(this::handleEvent);
    }

    @Test
    public void testInitialPosition() {
        assertEquals(INIT_X, saw.getX(), "Saw's x-position is incorrect");
        assertEquals(INIT_Y, saw.getY(), "Saw's y-position is incorrect");
        assertEquals(DIAMETER, saw.getWidth(), "Saw's width is incorrect");
        assertEquals(HEIGHT, saw.getHeight(), "Saw's height is incorrect");
    }

    @Test
    public void testEventSentOnContact() {
        Body box = createBody(INIT_X - DIAMETER * 2, INIT_Y, DIAMETER, DIAMETER);
        box.setLinearVelocity(5, 0);

        for (int i = 0; i < ITERATIONS; i++) step();

        assertEquals(1, events.size(), "There was not posted any event on saw-contact!");
        assertInstanceOf(EventDamage.class, events.get(0), "The event on saw-contact was not " + EventDamage.class);
        assertEquals(box.getFixtureList().get(0), ((EventDamage) events.get(0)).fixture(), "The colliding fixture was incorrect!");
    }

    @Test
    public void testMultipleEventsOnMultipleContacts() {
        Body box1 = createBody(INIT_X - DIAMETER * 2, INIT_Y, DIAMETER, DIAMETER);
        box1.setLinearVelocity(5, 0);
        Body box2 = createBody(INIT_X + DIAMETER * 2, INIT_Y, DIAMETER, DIAMETER);
        box2.setLinearVelocity(-5, 0);

        for (int i = 0; i < ITERATIONS; i++) step();

        assertEquals(2, events.size(), "There was not posted 2 events on 2 saw-contacts!");
        assertInstanceOf(EventDamage.class, events.get(0), "The event on saw-contact was not " + EventDamage.class);
        assertInstanceOf(EventDamage.class, events.get(1), "The event on saw-contact was not " + EventDamage.class);
        assertEquals(box1.getFixtureList().get(0), ((EventDamage) events.get(0)).fixture(), "The colliding fixture was incorrect!");
        assertEquals(box2.getFixtureList().get(0), ((EventDamage) events.get(1)).fixture(), "The colliding fixture was incorrect!");
    }

    @Test
    public void testMultipleSawsEvent() {
        Body box = createBody(INIT_X + DIAMETER / 2, INIT_Y + DIAMETER * 2, DIAMETER, DIAMETER);
        box.setLinearVelocity(0,-1);
        Saw saw2 = createSaw(DIAMETER, 0);
        contactListeners.add(saw2);

        for (int i = 0; i < ITERATIONS; i++) step();

        assertEquals(2, events.size(), "There should be posted two events!");
        assertInstanceOf(EventDamage.class, events.get(0), "The event was not " + EventDamage.class);
        assertInstanceOf(EventDamage.class, events.get(1), "The event was not " + EventDamage.class);
        assertEquals(box.getFixtureList().get(0), ((EventDamage) events.get(0)).fixture(), "The fixture in the event is incorrect");
        assertEquals(box.getFixtureList().get(0), ((EventDamage) events.get(1)).fixture(), "The fixture in the event is incorrect");
    }
}
