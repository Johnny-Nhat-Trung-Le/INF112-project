package inf112.skeleton.app.view.texturepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.view.ViewableTile;

import java.util.HashMap;
import java.util.Map;

public class TexturePack implements ITexturePack {

    // BRUH IS THIS EVEN CORRECT, WHAT IN DA AACTUAL FUUUUUUU
    private static final Map<String, TextureRegion> textureRegionMap = new HashMap<>();
    private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/gameAtlas.atlas"));

    public static final String TILE_0 = "tile_0";

    static {
        new TextureRegion(atlas.findRegion("tile", 0));
        textureRegionMap.put(TILE_0, new TextureRegion(new Texture("Tiles/tile_0000.png")));
    }


    @Override
    public TextureRegion test() {
             return new TextureRegion(atlas.findRegion("tile", 0));
    }
    @Override
    public TextureRegion getTexture(String key) {
        return textureRegionMap.get(key);
    }

    @Override
    public TextureRegion getTileTexture(ViewableTile tile) {
        return getTexture(tile.getTextureKey());
    }
}