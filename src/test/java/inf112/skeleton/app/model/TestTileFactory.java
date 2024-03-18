package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.tiles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTileFactory {
    private TileFactory factory;
    private World world;
    private EventBus eventBus;
    private static final float GRAVITY_X = 0;
    private static final float GRAVITY_Y = 0;

    @BeforeEach
    public void setUp() {
        factory = new TileFactory();
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
        eventBus = new EventBus();

        // hmm maybe not BeforeEach
        TileGround.loadStatic();
        TileFloatingGroundSlim.loadStatic();
        TileFloatingGround.loadStatic();
        TileFloatingGroundSingle.loadStatic();
    }

    @Test
    public void testGenerateTileGround() {
        String input = "G";
        List<TileModel> tiles = factory.generate(input, world, eventBus);
        assertEquals(1, tiles.size(), "Should generate one tile object");
        assertInstanceOf(TileGround.class, tiles.get(0), "Should generate a TileGround tile object");
    }

    @Test
    public void testGenerateTileFloatingGroundSlim() {
        String input = "w";
        List<TileModel> tiles = factory.generate(input, world, eventBus);
        assertEquals(1, tiles.size(), "Should generate one tile object");
        assertInstanceOf(TileFloatingGroundSlim.class, tiles.get(0), "Should generate a TileFloatingGroundSlim tile object");
    }

    @Test
    public void testMultipleTileObjects() {
        String input = """
                --GGG---GGG
                -GGGGG-GGGGG
                --GGGGGGGGG
                ---GGGGGGG
                ----GGGGG
                -----GGG
                ------G
                """;
        List<TileModel> tiles = factory.generate(input, world, eventBus);
        assertEquals(41, tiles.size(), "Should generate twelve tile objects");
    }

    // DO NOT OPEN *aware*
    @Test
    public void testCheckPosition() {
        // x = 0, y = 10
        // x = 0, y = 5 | x = 5, y = 5
        // x = 0, y = 0 | x = 5, y = 0 | x = 10, y = 0
        String input = """
                g               
                Gg
                gwi
                """;
        List<TileModel> tiles = factory.generate(input, world, eventBus);
        assertEquals(6, tiles.size(), "Should generate six tile objects");

        // Check positions and sizes
        assertTrue(tiles.get(0) instanceof TileFloatingGround, "First tile should be TileFloatingGround");
        assertEquals(0, tiles.get(0).getX(), "First tile should be at x=0");
        assertEquals(10, tiles.get(0).getY(), "First tile should be at y=10");
        assertEquals(TileFloatingGround.TILE_WIDTH, tiles.get(0).getWidth(), "First tile should have correct width");
        assertEquals(TileFloatingGround.TILE_HEIGHT, tiles.get(0).getHeight(), "First tile should have correct height");

        assertTrue(tiles.get(1) instanceof TileGround, "Second tile should be TileGround");
        assertEquals(0, tiles.get(1).getX(), "Second tile should be at x=0");
        assertEquals(5, tiles.get(1).getY(), "Second tile should be at y=5");
        assertEquals(TileGround.TILE_WIDTH, tiles.get(1).getWidth(), "Second tile should have correct width");
        assertEquals(TileGround.TILE_HEIGHT, tiles.get(1).getHeight(), "Second tile should have correct height");

        assertTrue(tiles.get(2) instanceof TileFloatingGround, "Third tile should be TileFloatingGround");
        assertEquals(5, tiles.get(2).getX(), "Third tile should be at x=5");
        assertEquals(5, tiles.get(2).getY(), "Third tile should be at y=5");
        assertEquals(TileFloatingGround.TILE_WIDTH, tiles.get(2).getWidth(), "Third tile should have correct width");
        assertEquals(TileFloatingGround.TILE_HEIGHT, tiles.get(2).getHeight(), "Third tile should have correct height");

        assertTrue(tiles.get(3) instanceof TileFloatingGround, "Fourth tile should be TileFloatingGround");
        assertEquals(0, tiles.get(3).getX(), "Fourth tile should be at x=0");
        assertEquals(0, tiles.get(3).getY(), "Fourth tile should be at y=0");
        assertEquals(TileFloatingGround.TILE_WIDTH, tiles.get(3).getWidth(), "Fourth tile should have correct width");
        assertEquals(TileFloatingGround.TILE_HEIGHT, tiles.get(3).getHeight(), "Fourth tile should have correct height");

        assertTrue(tiles.get(4) instanceof TileFloatingGroundSlim, "Fifth tile should be TileFloatingGroundSlim");
        assertEquals(5, tiles.get(4).getX(), "Fifth tile should be at x=5");
        assertEquals(0, tiles.get(4).getY(), "Fifth tile should be at y=0");
        assertEquals(TileFloatingGround.TILE_WIDTH, tiles.get(4).getWidth(), "Fifth tile should have correct width");
        assertEquals(TileFloatingGround.TILE_HEIGHT, tiles.get(4).getHeight(), "Fifth tile should have correct height");

        assertTrue(tiles.get(5) instanceof TileFloatingGroundSingle, "Sixth tile should be TileFloatingGroundSingle");
        assertEquals(10, tiles.get(5).getX(), "Sixth tile should be at x=5");
        assertEquals(0, tiles.get(5).getY(), "Sixth tile should be at y=0");
        assertEquals(TileFloatingGround.TILE_WIDTH, tiles.get(5).getWidth(), "Sixth tile should have correct width");
        assertEquals(TileFloatingGround.TILE_HEIGHT, tiles.get(5).getHeight(), "Sixth tile should have correct height");
    }
}
