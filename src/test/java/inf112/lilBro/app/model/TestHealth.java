package inf112.lilBro.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.lilBro.app.event.EventBus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHealth {

    private static final float GRAVITY_X = 0;
    private static final float GRAVITY_Y = 0;
    private static final float INIT_X = 0;
    private static final float INIT_Y = 0;
    private final World world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
    private final EventBus bus = new EventBus();
    private final PlayerModel player = new PlayerModel(bus, world, INIT_X, INIT_Y);
    private IHealth health = new Health(player, bus, 3, 5);
    private final int initialHealth = health.getHealth();
    private final int maxHealth = health.getMaxHealth();

    @Test
    public void testGetHealth() {
        assertEquals(3,initialHealth, "Health should be 3");
    }

    @Test
    public void testGetMaxHealth() {
        assertEquals(5, maxHealth, "Max health should be 5");
    }

    @Test
    public void testDamage() {
        health.damage(1);
        int healthAfterDamage = health.getHealth();
        assertTrue(initialHealth > healthAfterDamage, "Health should be lower than initial health");
    }

    @Test
    public void testRegenerate() {
        health.regenerate(1);
        int healthAfterRegen = health.getHealth();
        assertTrue(initialHealth < healthAfterRegen, "Health should be higher than initial health");
    }

    @Test
    public void testMaxHealth() {
        health.regenerate(100);
        int regeneratedHealth = health.getHealth();
        assertTrue(initialHealth < regeneratedHealth, "Health should be higher than initial health");
        assertEquals(maxHealth, regeneratedHealth, "Health should not exceed Max Health");
    }

    @Test
    public void testNotNegativeHealth() {
        health.damage(100);
        int damagedHealth = health.getHealth();
        assertTrue(initialHealth > damagedHealth, "Health should be lower than initial health");
        assertEquals(0,damagedHealth,"Health should not go lower than 0");
    }

}
