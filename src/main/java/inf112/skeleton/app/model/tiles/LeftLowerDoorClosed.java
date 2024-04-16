package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.TileFactory;

public class LeftLowerDoorClosed extends TileModel {
    public static final char KEY = 'd';

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
    public LeftLowerDoorClosed(World world, EventBus bus, float x, float y) {
        super(world, bus, x, y);
    }

    @Override
    protected Shape createShape(float w, float h) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0, TILE_HEIGHT / 4 + TILE_HEIGHT / 14);
        return shape;
    }
}
