package inf112.skeleton.app.view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.PlayerModel;
import inf112.skeleton.app.model.TileFactory;
import inf112.skeleton.app.model.effect.EffectJumpBoost;
import inf112.skeleton.app.model.effect.EffectSpeedBoost;
import inf112.skeleton.app.model.item.ItemEnergy;
import inf112.skeleton.app.model.item.ItemMushroom;
import inf112.skeleton.app.model.tiles.TileFloatingGroundSingleSlim;
import inf112.skeleton.app.model.tiles.TileGroundLeft;
import inf112.skeleton.app.model.tiles.contactableTiles.Door1;
import inf112.skeleton.app.model.tiles.contactableTiles.Spike;
import inf112.skeleton.app.view.texturepack.ITexturePack;
import inf112.skeleton.app.view.texturepack.TexturePack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    private static TextureAtlas atlas;
    private final float tileWidth = 10;
    private final float tileHeight = 2;
    private final String TILE = "tile";
    private final ITexturePack texturePack = new TexturePack();
    private World world;
    private EventBus bus;
    private PlayerModel player;

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
        TileFactory.initialize();
        atlas = new TextureAtlas(Gdx.files.internal("atlas/gameAtlas.atlas"));
    }

    private void step() {
        player.step(DT);
        world.step(DT, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
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

    @BeforeEach
    public void reset() {
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
        bus = new EventBus();
        player = new PlayerModel(bus, world, INIT_X, INIT_Y);
        world.setContactListener(player);
        createBody(0);
    }

    @Test
    public void getPlayerTextureLeft() {
        createBody(-tileWidth);
        createBody(-tileWidth * 2);
        player.moveLeft(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        TextureRegion leftTexture = texturePack.getPlayerTexture(player.getState(), DT);
        assertNotNull(leftTexture);
        assertEquals("Pink_Monster/Pink_Monster_LeftRun_6.png", leftTexture.getTexture().toString());
    }

    @Test
    public void getPlayerTextureRight() {
        createBody(tileWidth);
        createBody(tileWidth * 2);
        player.moveRight(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        TextureRegion rightTexture = texturePack.getPlayerTexture(player.getState(), DT);
        assertNotNull(rightTexture);
        assertEquals("Pink_Monster/Pink_Monster_Run_6.png", rightTexture.getTexture().toString());
    }

    @Test
    public void getPlayerTextureIdleRight() {
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        TextureRegion idleTexture = texturePack.getPlayerTexture(player.getState(), DT);
        assertNotNull(idleTexture);
        assertEquals("Pink_Monster/Pink_Monster_Idle_4.png", idleTexture.getTexture().toString());
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
        assertEquals("Pink_Monster/Pink_Monster_Idle_Left_4.png", idleTexture.getTexture().toString());
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
        assertEquals("Pink_Monster/Pink_Monster_Jump_6.png", jumpTexture.getTexture().toString());
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
        assertEquals("Pink_Monster/Pink_Monster_LeftJump_6.png", jumpLeftTexture.getTexture().toString());
    }

    @Test
    public void testGetItemEnergyTexture() {
        ItemEnergy energy = new ItemEnergy(bus, world, 0, 0);
        TextureRegion actualEnergyTexture = atlas.findRegion(TILE, 156);
        assertNotNull(texturePack.getItemTexture(energy));
        assertEquals(actualEnergyTexture.getRegionX(), texturePack.getItemTexture(energy).getRegionX(), " Should have the same texturePos X");
        assertEquals(actualEnergyTexture.getRegionY(), texturePack.getItemTexture(energy).getRegionY(), "Should have the same texturePos Y");
        assertEquals(actualEnergyTexture.getRegionWidth(), texturePack.getItemTexture(energy).getRegionWidth(), "Should have the same textureWidth");
        assertEquals(actualEnergyTexture.getRegionHeight(), texturePack.getItemTexture(energy).getRegionHeight(), "Should have the same textureHeight");
    }

    @Test
    public void testGetMushroomTexture() {
        ItemMushroom mushroom = new ItemMushroom(bus, world, 0, 0);
        TextureRegion actualMushroomTexture = atlas.findRegion(TILE, 106);
        assertNotNull(texturePack.getItemTexture(mushroom));
        assertEquals(actualMushroomTexture.getRegionX(), texturePack.getItemTexture(mushroom).getRegionX(), " Should have the same texturePos X");
        assertEquals(actualMushroomTexture.getRegionY(), texturePack.getItemTexture(mushroom).getRegionY(), "Should have the same texturePos Y");
        assertEquals(actualMushroomTexture.getRegionWidth(), texturePack.getItemTexture(mushroom).getRegionWidth(), "Should have the same textureWidth");
        assertEquals(actualMushroomTexture.getRegionHeight(), texturePack.getItemTexture(mushroom).getRegionHeight(), "Should have the same textureHeight");
    }

    @Test
    public void testGetEffectTexture() {
        EffectSpeedBoost speed = new EffectSpeedBoost(0);
        EffectJumpBoost jump = new EffectJumpBoost(0);
        assertNotNull(texturePack.getEffectTexture(speed));
        assertNotNull(texturePack.getEffectTexture(jump));
        assertEquals(atlas.findRegion(TILE, 156).getRegionX(), texturePack.getEffectTexture(speed).getRegionX(), " Should have the same texturePos X");
        assertEquals(atlas.findRegion(TILE, 106).getRegionX(), texturePack.getEffectTexture(jump).getRegionX(), " Should have the same texturePos X");
        assertEquals(atlas.findRegion(TILE, 156).getRegionY(), texturePack.getEffectTexture(speed).getRegionY(), "Should have the same texturePos Y");
        assertEquals(atlas.findRegion(TILE, 106).getRegionY(), texturePack.getEffectTexture(jump).getRegionY(), "Should have the same texturePos Y");
    }

    @Test
    public void testGetHpTexture() {
        TextureRegion actualHpTexture = atlas.findRegion(TILE, 139);
        assertNotNull(texturePack.getHpTexture());
        assertEquals(actualHpTexture.getRegionX(), texturePack.getHpTexture().getRegionX(), " Should have the same texturePos X");
        assertEquals(actualHpTexture.getRegionY(), texturePack.getHpTexture().getRegionY(), "Should have the same texturePos Y");
        assertEquals(actualHpTexture.getRegionWidth(), texturePack.getHpTexture().getRegionWidth(), "Should have the same textureWidth");
        assertEquals(actualHpTexture.getRegionHeight(), texturePack.getHpTexture().getRegionHeight(), "Should have the same textureHeight");
    }

    @Test
    public void testGetDifferentTextures() {
        Door1 door1 = new Door1(world, bus, 0, 0);
        TextureRegion actualDoor1 = atlas.findRegion(TILE, 7);
        assertNotNull(texturePack.getTileTexture(door1));
        assertEquals(actualDoor1.getRegionX(), texturePack.getTileTexture(door1).getRegionX(), " Should have the same texturePos X");
        assertEquals(actualDoor1.getRegionY(), texturePack.getTileTexture(door1).getRegionY(), "Should have the same texturePos Y");
        assertEquals(actualDoor1.getRegionWidth(), texturePack.getTileTexture(door1).getRegionWidth(), "Should have the same textureWidth");
        assertEquals(actualDoor1.getRegionHeight(), texturePack.getHpTexture().getRegionHeight(), "Should have the same textureHeight");

        TileGroundLeft tgl = new TileGroundLeft(world, bus, 0, 0);
        TextureRegion actualTgl = atlas.findRegion(TILE, 1);
        assertNotNull(texturePack.getTileTexture(tgl));
        assertEquals(actualTgl.getRegionX(), texturePack.getTileTexture(tgl).getRegionX(), " Should have the same texturePos X");
        assertEquals(actualTgl.getRegionY(), texturePack.getTileTexture(tgl).getRegionY(), "Should have the same texturePos Y");
        assertEquals(actualTgl.getRegionWidth(), texturePack.getTileTexture(tgl).getRegionWidth(), "Should have the same textureWidth");
        assertEquals(actualTgl.getRegionHeight(), texturePack.getTileTexture(tgl).getRegionHeight(), "Should have the same textureHeight");


        TileFloatingGroundSingleSlim tfgsl = new TileFloatingGroundSingleSlim(world, bus, 0, 0);
        TextureRegion actualTfgsl = atlas.findRegion(TILE, 64);
        assertNotNull(texturePack.getTileTexture(tfgsl));
        assertEquals(actualTfgsl.getRegionX(), texturePack.getTileTexture(tfgsl).getRegionX(), " Should have the same texturePos X");
        assertEquals(actualTfgsl.getRegionY(), texturePack.getTileTexture(tfgsl).getRegionY(), "Should have the same texturePos Y");
        assertEquals(actualTfgsl.getRegionWidth(), texturePack.getTileTexture(tfgsl).getRegionWidth(), "Should have the same textureWidth");
        assertEquals(actualTfgsl.getRegionHeight(), texturePack.getTileTexture(tfgsl).getRegionHeight(), "Should have the same textureHeight");

        Spike spike = new Spike(world, bus, 0, 0);
        TextureRegion actualSpike = atlas.findRegion(TILE, 46);
        assertNotNull(texturePack.getTileTexture(spike));
        assertEquals(actualSpike.getRegionX(), texturePack.getTileTexture(spike).getRegionX(), " Should have the same texturePos X");
        assertEquals(actualSpike.getRegionY(), texturePack.getTileTexture(spike).getRegionY(), "Should have the same texturePos Y");
        assertEquals(actualSpike.getRegionWidth(), texturePack.getTileTexture(spike).getRegionWidth(), "Should have the same textureWidth");
        assertEquals(actualSpike.getRegionHeight(), texturePack.getTileTexture(spike).getRegionHeight(), "Should have the same textureHeight");


    }
}
