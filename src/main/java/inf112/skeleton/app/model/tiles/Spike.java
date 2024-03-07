package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.model.TileFactory;

public class Spike extends TileModel {

    public static void loadStatic(){}

    static {
        TileFactory.register('S', (world, eventBus, x, y) -> {
            return new Spike(world, x + TILE_WIDTH / 2, y + TILE_HEIGHT / 4, TILE_WIDTH/2, TILE_HEIGHT/2);
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
    public Spike(World world, float x, float y, float w, float h) {
        super(world, x, y, w, h);
    }

    @Override
    protected Shape createShape(float w, float h) {
        Vector2[] vecBottom = new Vector2[3];
        vecBottom[0] = new Vector2(-TILE_WIDTH / 2, -TILE_HEIGHT / 2); // BL
        vecBottom[1] = new Vector2(TILE_WIDTH / 2, -TILE_HEIGHT / 2); // BR
        vecBottom[2] = new Vector2(0, TILE_HEIGHT/4+TILE_HEIGHT/14); // C
        PolygonShape shape= new PolygonShape();
        shape.set(vecBottom);
        return shape;
    }
}