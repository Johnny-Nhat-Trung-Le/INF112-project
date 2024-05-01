package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.tiles.TileModel;

public class Saw extends DamageTileModel {
    public static final char KEY = 's';
    private static final int DAMAGE = 1;

    /**
     * Creates a {@link TileModel} with default size and position.
     * <p>
     * Used for {@link inf112.skeleton.app.model.TileFactory}.
     *
     * @param world that the body is added to
     * @param bus   that is used for handling and posting {@link inf112.skeleton.app.event.Event}s
     * @param x     left-most position of tile in the horizontal axis
     * @param y     bottom-most position of tile in the vertical axis
     */
    public Saw(World world, EventBus bus, float x, float y) {
        this(world, bus, x + TILE_WIDTH / 2, y + TILE_HEIGHT / 3, TILE_WIDTH * 2 / 3);
    }

    /**
     * Creates a {@link TileModel} and places its body in
     * the specified {@link World}.
     *
     * @param world that the body is added to
     * @param x     center position in the horizontal axis
     * @param y     center position in the vertical axis
     * @param d     diameter of body (equal to width and height/2)
     */
    public Saw(World world, EventBus bus, float x, float y, float d) {
        super(world, bus, x, y, d, d, DAMAGE);
    }

    @Override
    protected Shape createShape(float w, float h) {
        CircleShape circle = new CircleShape();
        circle.setRadius(w / 2);
        return circle;
    }

    @Override
    protected Body createBody(float x, float y) {
        return super.createBody(x, y - getHeight() / 2);
    }

    @Override
    public float getY() {
        return body.getPosition().y;
    }

}