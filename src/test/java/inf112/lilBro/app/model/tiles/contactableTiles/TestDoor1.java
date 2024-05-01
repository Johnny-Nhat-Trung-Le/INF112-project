package inf112.lilBro.app.model.tiles.contactableTiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.event.EventReachedDoor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestDoor1 {
    private static final int ITERATIONS = 600;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final float TIME_STEP = 1 / (float) 60;
    private static final float INIT_X = 10;
    private static final float INIT_Y = 20;
    private static final float WIDTH = 5;
    private static final float HEIGHT = 5;

    private EventBus bus;
    private World world;
    private Door1 door;
    private List<Event> events;

    private void handleEvent(Event e) {
        events.add(e);
    }

    private void step() {
        world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    /**
     * @param x      left-most
     * @param y      bottom-most
     * @param width  full
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

    @BeforeEach
    public void beforeEach() {
        bus = new EventBus();
        world = new World(new Vector2(0, 0), true);
        door = new Door1(world, bus, INIT_X + WIDTH / 2, INIT_Y + HEIGHT / 2, WIDTH, HEIGHT);
        events = new ArrayList<>();

        world.setContactListener(door);
        bus.addEventHandler(this::handleEvent);
    }

    @Test
    public void testInitialPosition() {
        assertEquals(INIT_X, door.getX(), "Door1's x-position is incorrect");
        assertEquals(INIT_Y, door.getY(), "Door1's y-position is incorrect");
        assertEquals(WIDTH, door.getWidth(), "Door1's width is incorrect");
        assertEquals(HEIGHT, door.getHeight(), "Door1's height is incorrect");
    }

    @Test
    public void testEventSentOnContact() {
        Body box = createBody(INIT_X - WIDTH * 2, INIT_Y, WIDTH, HEIGHT);
        box.setLinearVelocity(5, 0);

        for (int i = 0; i < ITERATIONS; i++) step();

        assertEquals(1, events.size(), "There was not posted any event on Door1-contact!");
        assertInstanceOf(EventReachedDoor.class, events.get(0), "The event on Door1-contact was not " + EventReachedDoor.class);
    }
}
