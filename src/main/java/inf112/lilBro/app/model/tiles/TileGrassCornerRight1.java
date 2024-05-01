package inf112.lilBro.app.model.tiles;

import com.badlogic.gdx.physics.box2d.World;
import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.TileFactory;

public class TileGrassCornerRight1 extends TileModel {
    public static final char KEY = 'z';

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
    public TileGrassCornerRight1(World world, EventBus bus, float x, float y) {
        super(world, bus, x, y);
    }
}
