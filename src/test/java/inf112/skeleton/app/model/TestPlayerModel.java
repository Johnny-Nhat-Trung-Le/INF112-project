package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlayerModel {
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final int NUM_ITERATIONS = 60;
    private static final float DT = 1 / 60f;
    private static final float INIT_X = 0;
    private static final float INIT_Y = 30;
    private static final float GRAVITY_X = 0;
    private static final float GRAVITY_Y = 0;
    private World world;
    private PlayerModel player;

    private void step() {
        player.step(DT);
        world.step(DT, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    @BeforeEach
    public void reset() {
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
        player = new PlayerModel(new EventBus(), world, INIT_X, INIT_Y);
        world.setContactListener(player);
    }

    @Test
    public void testInitialState() {
        assertEquals(INIT_X, player.getX(), "Player does not start at the horizontal position specified in the initialization! expected: " + INIT_X + ", got: " + player.getX());
        assertEquals(INIT_Y, player.getY(), "Player does not start at the vertical position specified in the initialization! expected: " + INIT_Y + ", got: " + player.getY());
    }

    @Test
    public void testStandStill() {
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            assertEquals(INIT_X, player.getX(), "Player horizontal position changes on step, without call to the move-methods!");
            assertEquals(INIT_Y, player.getY(), "Player vertical position changes on step, without call to the move-methods!");
        }
    }

    @Test
    public void testMoveRight() {
        float lastX = INIT_X;

        player.moveRight(true);

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            assertTrue(lastX < player.getX(), "Player is not moving after moveRight(true) has been called!");
            assertEquals(INIT_Y, player.getY(), "Player is moving in the vertical axis when moveRight(true) has been called!");
            lastX = player.getX();
        }
    }

    @Test
    public void testMoveLeft() {
        float lastX = INIT_X;

        player.moveLeft(true);

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            assertTrue(lastX > player.getX(), "Player is not moving after moveRight(true) has been called!");
            assertEquals(INIT_Y, player.getY(), "Player is moving in the vertical axis when moveRight(true) has been called!");
            lastX = player.getX();
        }
    }

    @Test
    public void testMoveDown() {
        float lastY = INIT_Y;

        player.moveDown(true);

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            assertTrue(lastY > player.getY(), "Player is not moving downwards after moveDown(true) has been called!");
            assertEquals(INIT_X, player.getX(), "Player is moving in the horizontal axis when moveDown(true) has been called!");
            lastY = player.getY();
        }
    }

    @Test
    public void testMoveUp() {
        // Create Static Body under Player
        float width = 10;
        float height = 2;
        BodyDef ground = new BodyDef();
        ground.type = BodyDef.BodyType.StaticBody;
        float groundY = INIT_Y - 10;
        ground.position.set(0, groundY);
        Body groundBody = world.createBody(ground);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;
        fixtureDef.shape = groundShape;
        groundBody.createFixture(fixtureDef);

        world.setGravity(new Vector2(GRAVITY_X, -20));

        for (int i = 0; i < NUM_ITERATIONS * 2; i++) {
            step();
        }
        float initialY = player.getY();
        float previousY = initialY;
        player.moveUp(true);
        while (true) {
            step();

            float currentY = player.getY();
            if (currentY < previousY) {
                player.moveUp(false);
                break;
            }

            previousY = currentY;
        }

        float topY = player.getY();
        assertTrue(topY > initialY);

        for (int i = 0; i < NUM_ITERATIONS * 2; i++) {
            step();
        }
        float finalY = player.getY();
        assertTrue(finalY < topY);
        assertEquals(finalY, initialY);
        // Reset the gravity
        world.setGravity(new Vector2(GRAVITY_X, GRAVITY_Y));
    }

}
