package inf112.skeleton.app.view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.PlayerModel;
import inf112.skeleton.app.model.tiles.TileGround;
import inf112.skeleton.app.view.texturepack.ITexturePack;
import inf112.skeleton.app.view.texturepack.TexturePack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTexturePack {

    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final int NUM_ITERATIONS = 60;
    private static final float DT = 1 / 60f;
    private static final float INIT_X = 0;
    private static final float INIT_Y = 30;
    private static final float GRAVITY_X = 0;
    private static final float GRAVITY_Y = -20;
    private final float tileWidth = 10;
    private final float tileHeight = 2;
    private World world;
    private PlayerModel player;
    private final ITexturePack texturePack = new TexturePack();

    @BeforeAll
    public static void beforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationListener() {
            @Override
            public void create() {

            }

            @Override
            public void resize(int width, int height) {

            }

            @Override
            public void render() {

            }

            @Override
            public void pause() {

            }

            @Override
            public void resume() {

            }

            @Override
            public void dispose() {

            }
        };
        new HeadlessApplication(listener, config);
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;

    }

    private void step() {
        player.step(DT);
        world.step(DT, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    @BeforeEach
    public void reset() {
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
        player = new PlayerModel(new EventBus(), world, INIT_X, INIT_Y);
        world.setContactListener(player);
        createBody(0);
    }

    @Test
    public void getPlayerTextureLeft() {
        createBody(-tileWidth);
        createBody(-tileWidth*2);
        player.moveLeft(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        TextureRegion leftTexture = texturePack.getPlayerTexture(player.getState(), DT);
        assertNotNull(leftTexture);
        assertEquals("Pink_Monster/Pink_Monster_LeftRun_6.png",leftTexture.getTexture().toString());
    }

    @Test
    public void getPlayerTextureRight() {
        createBody(tileWidth);
        createBody(tileWidth*2);
        player.moveRight(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        TextureRegion rightTexture = texturePack.getPlayerTexture(player.getState(), DT);
        assertNotNull(rightTexture);
        assertEquals("Pink_Monster/Pink_Monster_Run_6.png",rightTexture.getTexture().toString());
    }

    @Test
    public void getPlayerTextureIdleRight() {
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        TextureRegion idleTexture = texturePack.getPlayerTexture(player.getState(), DT);
        assertNotNull(idleTexture);
        assertEquals("Pink_Monster/Pink_Monster_Idle_4.png",idleTexture.getTexture().toString());
    }

    @Test
    public void getPlayerTextureIdleLeft() {
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        player.moveLeft(true);
        step();
        player.moveLeft(false);
        step();
        TextureRegion idleTexture = texturePack.getPlayerTexture(player.getState(), DT);
        assertNotNull(idleTexture);
        assertEquals("Pink_Monster/Pink_Monster_Idle_Left_4.png",idleTexture.getTexture().toString());
    }

    @Test
    public void getPlayerTextureJump() {
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        player.moveUp(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        TextureRegion jumpTexture = texturePack.getPlayerTexture(player.getState(), DT);
        assertNotNull(jumpTexture);
        assertEquals("Pink_Monster/Pink_Monster_Jump_6.png",jumpTexture.getTexture().toString());
    }

    @Test
    public void getPlayerTextureJumpLeft() {
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        player.moveUp(true);
        player.moveLeft(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        TextureRegion jumpLeftTexture = texturePack.getPlayerTexture(player.getState(), DT);
        assertNotNull(jumpLeftTexture);
        assertEquals("Pink_Monster/Pink_Monster_LeftJump_6.png",jumpLeftTexture.getTexture().toString());
    }


    private void createBody(float x) {
        BodyDef ground = new BodyDef();
        ground.type = BodyDef.BodyType.StaticBody;
        float groundY = INIT_Y - 10;
        ground.position.set(x, groundY);
        Body groundBody = world.createBody(ground);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(tileWidth / 2, tileHeight / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;
        fixtureDef.shape = groundShape;
        groundBody.createFixture(fixtureDef);


    }


}
