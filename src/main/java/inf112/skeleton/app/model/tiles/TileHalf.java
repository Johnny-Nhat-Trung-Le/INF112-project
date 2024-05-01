package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.TileFactory;

public abstract class TileHalf extends TileModel {

    /**
     * Creates a {@link TileModel} and places its body in
     * the specified {@link World}.
     *
     * @param world that the body is added to
     * @param x     center position in the horizontal axis
     * @param y     center position in the vertical axis
     * @param w     width of body
     * @param h     height of body
     */
    public TileHalf(World world, float x, float y, float w, float h) {
        super(world, x, y, w, h);
    }

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
    public TileHalf(World world, EventBus bus, float x, float y) {
        super(world, bus, x, y);
    }

    @Override
    protected Shape createShape(float w, float h) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(TILE_WIDTH / 2, TILE_HEIGHT / 4, new Vector2(0, TILE_HEIGHT / 4), 0);
        return shape;
    }
}
