package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.PlayerModel;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.model.tiles.contactableTiles.Saw;
import inf112.skeleton.app.model.tiles.contactableTiles.Spike;
import inf112.skeleton.app.utils.ContactListeners;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private void step() {
        player.step(DT);
        world.step(DT, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    @BeforeEach
    public void reset() {
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
        bus = new EventBus();
        player = new PlayerModel(bus, world, INIT_X, INIT_Y);
        contactListeners = new ContactListeners();
        contactListeners.add(player);
        world.setContactListener(contactListeners);
    }

    @Test
    public void testSpike() {
        Spike spike = new Spike(world, bus, x, player.getY(), width, height);
        int init_Hp = player.getHp();
        contactListeners.add(spike);
        player.moveRight(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        assertTrue(init_Hp > player.getHp(), "Player is suppose to lose 1 hp");
        assertEquals(init_Hp - 1, player.getHp());
    }

    @Test
    public void testSaw() {
        Saw saw = new Saw(world, bus, player.getX() - player.getWidth(), player.getY(), width, height);
        int init_Hp = player.getHp();
        contactListeners.add(saw);
        player.moveLeft(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();

        }
        assertTrue(init_Hp > player.getHp(), "Player is suppose to lose 1 hp");
        assertEquals(init_Hp - 1, player.getHp());

    }

    @Test
    public void testImmunityDoubleSaw() {
        Saw saw = new Saw(world, bus, player.getX() - player.getWidth(), player.getY(), width, height);
        Saw saw1 = new Saw(world, bus, saw.getX() - saw.getWidth(), saw.getY(), width, height);
        contactListeners.add(saw);
        contactListeners.add(saw1);
        int init_Hp = player.getHp();
        player.moveLeft(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        assertTrue(init_Hp > player.getHp(), "Player is suppose to lose 1 hp");
        assertEquals(init_Hp - 1, player.getHp());
    }
}
