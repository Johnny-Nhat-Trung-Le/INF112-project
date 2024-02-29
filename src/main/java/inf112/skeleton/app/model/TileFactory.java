package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.utils.Function4;

import java.util.*;

public class TileFactory {
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
}
