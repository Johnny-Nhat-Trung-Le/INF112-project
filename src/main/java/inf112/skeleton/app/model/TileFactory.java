package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.utils.Function4;
import inf112.skeleton.app.utils.PluginLoader;

import java.util.*;

public class TileFactory {
    /**
     * Maps a key ({@linkplain Character}) to a factory ({@linkplain Function4})
     * that takes in:
     * <p>
     * {@link World} to be created in <p>
     * {@link EventBus} that {@link inf112.skeleton.app.event.Event}s are posted and handled from <p>
     * {@link Float} left-most tile-position <p>
     * {@link Float} bottom-most tile-position
     * <p>
     * The factory creates a {@linkplain TileModel} on call.
     */
    public static final Map<Character, Function4<World, EventBus, Float, Float, TileModel>> translation = new HashMap<>();

    /**
     * Creates a {@link List} containing {@link TileModel}s.
     * Where the tiles are created in the specified {@link World}.
     *
     * @param s     string where each character represents a {@link TileModel}
     * @param world the {@link World} the tiles are created in
     * @return the generated {@link List}
     */
    public static List<TileModel> generate(String s, World world, EventBus bus) {
        String[] lines = s.split("\n");

        int rows = lines.length;
        int cols = Arrays.stream(lines).map(String::length).max(Integer::compareTo).orElse(0);

        List<TileModel> tiles = new ArrayList<>(rows * cols);

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            for (int j = 0; j < line.length(); j++) {
                char key = line.charAt(j);

                if (translation.containsKey(key)) {
                    float x = TileModel.TILE_WIDTH * j;
                    float y = TileModel.TILE_HEIGHT * (rows - 1 - i);
                    TileModel tile = translation.get(key).apply(world, bus, x, y);
                    tiles.add(tile);
                }
            }
        }

        return tiles;
    }

    /**
     * Registers a type of tile to the {@link TileFactory}.
     *
     * @param key      the {@link Character} representing the tile in the {@link TileFactory}::generate-call
     * @param function where {@link World} is the world that the {@link TileModel}s are added to.
     *                 {@link EventBus} is the used to call events from interactions with the tile.
     *                 The {@link Float}s are in order left-most x-position and bottom-most y-position of the tile.
     *                 {@link TileModel} is tile created
     */
    public static void register(char key, Function4<World, EventBus, Float, Float, TileModel> function) {
        translation.put(key, function);
    }

    /**
     * Initialize {@code TileModel.translation} with keys to factories.
     */
    public static void initialize() {
        PluginLoader.loadClasses(TileFactory.class, "./tiles", TileModel.class).forEach(c -> {
            Character key = PluginLoader.getConstant(c, "KEY", char.class);
            Function4<World, EventBus, Float, Float, TileModel> factory = PluginLoader.makeFactory(c, World.class, EventBus.class, Float.TYPE, Float.TYPE);
            if (factory != null && key != null) {
                translation.put(key, factory);
//                Gdx.app.log("ModelFactory", String.format("Loaded ‘%s’ (from %s)", key, c.getName()));
            }
        });
    }
}
