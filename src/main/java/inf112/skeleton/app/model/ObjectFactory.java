package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.World;

import java.util.List;

public abstract class ObjectFactory {
    /**
     * Creates a {@link List} containing {@link TileModel}s.
     * Where the tiles are created in the specified {@link World}.
     * @param s string where each character represents a {@link TileModel}
     * @param world the {@link World} the tiles are created in
     * @return the generated {@link List}
     */
    abstract List<TileModel> generate(String s, World world);
}
