package inf112.lilBro.app.model.tiles.contactableTiles;

import com.badlogic.gdx.physics.box2d.World;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.tiles.TileModel;

public class Door1 extends DoorModel {
    public static final char KEY = '9';

    /**
     * Creates a {@link TileModel} with default size and position.
     * <p>
     * Used for {@link inf112.lilBro.app.model.TileFactory}.
     *
     * @param world that the body is added to
     * @param bus   that is used for handling and posting {@link inf112.lilBro.app.event.Event}s
     * @param x     left-most position of the tile
     * @param y     bottom-most position of the tile
     */
    public Door1(World world, EventBus bus, float x, float y) {
        this(world, bus, x + TILE_WIDTH / 2, y - TILE_HEIGHT / 4, TILE_WIDTH / 2, TILE_HEIGHT / 2);
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
    public Door1(World world, EventBus bus, float x, float y, float w, float h) {
        super(world, bus, x, y, w, h);
        body.getFixtureList().get(0).setUserData(this);
    }
}
