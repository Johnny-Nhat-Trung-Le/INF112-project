package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTileModel {
    private static final float x = 10;
    private static final float y = 10;
    private static final float width = TileModel.TILE_WIDTH;
    private static final float height = TileModel.TILE_HEIGHT;
    private static final float expectedX = x - width / 2;
    private static final float expectedY = y - height / 2;

    private World world;

    @BeforeEach
    public void setUp() {
        world = new World(new Vector2(0, 0), true);
    }

    @Test
    public void testTileModel() {
        TileModelTemp tileModel = new TileModelTemp(world, x, y, width, height);


        assertEquals(expectedX, tileModel.getX(), "TileModel X position should match expected");
        assertEquals(expectedY, tileModel.getY(), "TileModel Y position should match expected");
        assertEquals(width, tileModel.getWidth(), "TileModel width should match width"); // hmm maybe better message
        assertEquals(height, tileModel.getHeight(), "TileModel height should match height");
    }

    @Test
    public void testTileHalf() {
        TileHalfTemp tileModel = new TileHalfTemp(world, x, y, width, height);

        assertEquals(expectedX, tileModel.getX(), "TileModel X position should match expected");
        assertEquals(expectedY, tileModel.getY(), "TileModel Y position should match expected");
        assertEquals(width, tileModel.getWidth(), "TileModel width should match width");
        assertEquals(height, tileModel.getHeight(), "TileModel height should match height");
    }

    // Temp Tile for Testing
    private static class TileModelTemp extends TileModel {
        public TileModelTemp(World world, float x, float y, float w, float h) {
            super(world, x, y, w, h);
        }
    }

    // Temp TileHalf for Testing
    private static class TileHalfTemp extends TileHalf {
        public TileHalfTemp(World world, float x, float y, float w, float h) {
            super(world, x, y, w, h);
        }
    }


}
