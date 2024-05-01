package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.TileFactory;

public class TileBottomRight extends TileModel {
    public static final char KEY = 'j';

    /**
     * Creates a {@link TileModel} with default width and height.
     * <p>
     * Used for {@link TileFactory}.
     *
     * @param world that the body is added to
     * @param bus   that is used for handling and posting {@link inf112.skeleton.app.event.Event}s
     * @param x     left-most position in the horizontal axis
     * @param y     bottom-most position in the vertical axis
     */
    public TileBottomRight(World world, EventBus bus, float x, float y) {
        super(world, bus, x, y);
    }
}
