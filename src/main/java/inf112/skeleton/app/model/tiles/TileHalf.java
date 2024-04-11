package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

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

    @Override
    protected Shape createShape(float w, float h) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(TILE_WIDTH / 2, TILE_HEIGHT / 4, new Vector2(0, TILE_HEIGHT / 4), 0);
        return shape;
    }
}
