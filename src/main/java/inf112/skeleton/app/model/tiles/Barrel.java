package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.TileFactory;

public class Barrel extends TileModel {
    public static final char KEY = 'B';

    /**
     * Creates a {@link TileModel} with default size and position.
     * <p>
     * Used for {@link TileFactory}.
     *
     * @param world that the body is added to
     * @param bus   that is used for handling and posting {@link Event}s
     * @param x     left-most position of tile in the horizontal axis
     * @param y     bottom-most position of tile in the vertical axis
     */
    public Barrel(World world, EventBus bus, float x, float y) {
        super(world, x + TILE_HEIGHT / 2, y + TILE_HEIGHT / 4, TILE_WIDTH / 2, TILE_HEIGHT / 2);
    }
}
