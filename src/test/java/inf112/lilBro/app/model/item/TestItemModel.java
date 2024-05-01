package inf112.lilBro.app.model.item;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.Durability;
import inf112.lilBro.app.model.event.EventItemUsedUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestItemModel {
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final float DT = 1 / 60f;
    private static final float INIT_X = 0;
    private static final float INIT_Y = 30;
    private static final float GRAVITY_X = 0;
    private static final float GRAVITY_Y = 0;
    private World world;
    private EventBus bus;
    private ItemModel item;
    private List<Event> events;

    private void step() {
        world.step(DT, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    private void handleEvent(Event e) {
        events.add(e);
    }

    @BeforeEach
    public void reset() {
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
        bus = new EventBus();
        item = new ItemEnergy(bus, world, INIT_X, INIT_Y);
        bus.addEventHandler(this::handleEvent);
        events = new ArrayList<>();
    }

    @Test
    public void testInitialPosition() {
        assertEquals(INIT_X, item.getX());
        assertEquals(INIT_Y, item.getY());
    }

    @Test
    public void testDurability() {
        Durability last = item.getDurability();

        for (int i = 0; i < last.maximum(); i++) {
            item.use();
            Durability dur = item.getDurability();

            assertEquals(last.remaining() - 1, dur.remaining(), "The durability did not reduce by one");
            assertEquals(last.maximum(), dur.maximum(), "The maximum durability changed");
            last = dur;
        }

        assertEquals(1, events.size(), "There is not posted exactly one event");
        assertInstanceOf(EventItemUsedUp.class, events.get(0), "There is not posted exactly one event");

        item.use();
        assertEquals(0, item.getDurability().remaining(), "Using the item when the durability is at 0 results in durability non-null");
    }
}
