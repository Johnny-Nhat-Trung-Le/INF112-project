package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.grid.IGrid;

public abstract class ObjectFactory {
    /**
     * Creates an {@link IGrid} containing {@link TileModel}s.
     * Where the tiles are created in the specified {@link World}.
     * @param s string where each character represents a {@link TileModel}
     * @param world the {@link World} the tiles are created in
     * @return the generated {@link IGrid}
     */
    abstract IGrid<TileModel> generate(String s, World world);
}
