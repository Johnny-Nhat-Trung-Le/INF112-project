package inf112.skeleton.app.view.texturePack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.model.tiles.*;
import inf112.skeleton.app.view.ViewableTile;
import inf112.skeleton.app.view.texturepack.TexturePack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTexturePack {
    private World world;
    private TexturePack texturePack;
    private static final String TILE_NAME = "tile";
    @BeforeAll
    public static void beforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationAdapter() {};
        new HeadlessApplication(listener, config);
    }
    @BeforeEach
    public void beforeEach() {
        world = new World(new Vector2(0, 0), true);
    }
    @Test
    public void testGetTileTexture() {
        texturePack = new TexturePack();
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/gameAtlas.atlas"));;
        List<ViewableTile> tiles = new ArrayList<>();

        tiles.add(new TileGround(world, 0, 0, TileModel.TILE_WIDTH, TileModel.TILE_HEIGHT));
        tiles.add(new TileFloatingGround(world, 0, 0, TileModel.TILE_WIDTH, TileModel.TILE_HEIGHT));
        tiles.add(new TileFloatingGroundSlim(world, 0, 0, TileModel.TILE_WIDTH, TileModel.TILE_HEIGHT));
        tiles.add(new TileFloatingGroundSingle(world, 0, 0, TileModel.TILE_WIDTH, TileModel.TILE_HEIGHT));

        for (ViewableTile tile : tiles) {
            TextureRegion textureRegion = texturePack.getTileTexture(tile);
            assertNotNull(textureRegion);

            if (tile instanceof TileGround) {
                assertEquals(new TextureRegion(atlas.findRegion(TILE_NAME, 2)), textureRegion);
            } else if (tile instanceof TileFloatingGround) {
                assertEquals(new TextureRegion(atlas.findRegion(TILE_NAME, 50)), textureRegion);
            } else if (tile instanceof TileFloatingGroundSlim) {
                assertEquals(new TextureRegion(atlas.findRegion(TILE_NAME, 66)), textureRegion);
            } else if (tile instanceof TileFloatingGroundSingle) {
                assertEquals(new TextureRegion(atlas.findRegion(TILE_NAME, 48)), textureRegion);
            }
        }
    }
}
