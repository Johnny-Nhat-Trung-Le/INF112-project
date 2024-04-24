package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.TileFactory;

public class TileGroundSingle extends TileModel {
    public static final char KEY = 'I';

    /**
     * Creates a {@link TileModel} with default width and height.
     * <p>
     * Used for {@link TileFactory}.
     *
     * @param world that the body is added to
     * @param bus   that is used for handling and posting {@link Event}s
     * @param x     left-most position in the horizontal axis
     * @param y     bottom-most position in the vertical axis
     */
    public TileGroundSingle(World world, EventBus bus, float x, float y) {
        super(world, bus, x, y);
    }
}
