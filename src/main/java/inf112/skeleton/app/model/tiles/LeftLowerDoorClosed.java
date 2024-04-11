package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.model.TileFactory;

public class LeftLowerDoorClosed extends TileModel {
    static {
        TileFactory.register('d', (world, eventBus, x, y) -> {
            return new LeftLowerDoorClosed(world, x + TILE_WIDTH / 2, y + TILE_HEIGHT / 4, TILE_WIDTH / 2, TILE_HEIGHT / 2);
        });

    }

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
    public LeftLowerDoorClosed(World world, float x, float y, float w, float h) {
        super(world, x, y, w, h);
    }

    public static void loadStatic() {
    }

    @Override
    protected Shape createShape(float w, float h) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0, TILE_HEIGHT / 4 + TILE_HEIGHT / 14);
        return shape;
    }
}
