package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.tiles.TileModel;


public class Spike extends DamageTileModel {
    public static final char KEY = 'S';
    private static final int DAMAGE = 1;

    /**
     * Creates a {@link TileModel} with default width and height.
     * <p>
     * Used for {@link inf112.skeleton.app.model.TileFactory}.
     *
     * @param world that the body is added to
     * @param bus   that is used for handling and posting {@link inf112.skeleton.app.event.Event}s
     * @param x     left-most position in the horizontal axis
     * @param y     bottom-most position in the vertical axis
     */
    public Spike(World world, EventBus bus, float x, float y) {
        // height=13/16, width=14/16, scale=2/3
        this(world, bus, x + TILE_WIDTH / 2, y + TILE_HEIGHT * 13 / 16 / 2 * 2 / 3, TILE_WIDTH * 14 / 16 * 2 / 3, TILE_HEIGHT * 13 / 16 * 2 / 3);
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
    public Spike(World world, EventBus bus, float x, float y, float w, float h) {
        super(world, bus, x, y, w, h, DAMAGE);
    }

    @Override
    protected Shape createShape(float w, float h) {
        Vector2[] vecBottom = new Vector2[3];
        vecBottom[0] = new Vector2(-w / 2, -h / 2); // BL
        vecBottom[1] = new Vector2(w / 2, -h / 2); // BR
        vecBottom[2] = new Vector2(0, h / 2); // C
        PolygonShape shape = new PolygonShape();
        shape.set(vecBottom);
        return shape;
    }
}