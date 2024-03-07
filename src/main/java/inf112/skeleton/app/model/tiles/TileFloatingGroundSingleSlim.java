package inf112.skeleton.app.model.tiles;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.model.TileFactory;

public class TileFloatingGroundSingleSlim extends TileHalf {

    public static void loadStatic(){}

    static {
        TileFactory.register('|', (world, eventBus, x, y) -> {
            return new TileFloatingGroundSingleSlim(world, x + TILE_WIDTH / 2, y + TILE_HEIGHT / 2, TILE_WIDTH, TILE_HEIGHT);
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
    public TileFloatingGroundSingleSlim(World world, float x, float y, float w, float h) {
        super(world, x, y, w, h);
    }


}