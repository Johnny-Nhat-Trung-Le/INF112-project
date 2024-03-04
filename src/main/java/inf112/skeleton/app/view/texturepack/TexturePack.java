package inf112.skeleton.app.view.texturepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.model.tiles.TileGround;
import inf112.skeleton.app.model.tiles.TileGroundCornerLeft;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.view.ViewableTile;

public class TexturePack implements ITexturePack {
    private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/gameAtlas.atlas"));
    public static final String TILE = "tile";
    // Legg til mer hvis vi vil ha andre sprite

    // Maybe switch-case :D
    @Override
    public TextureRegion getTileTexture(ViewableTile tile) {
        if (tile instanceof TileGroundCornerLeft) {
            return new TextureRegion(atlas.findRegion(TILE, 49));
        }
        if (tile instanceof TileGround) {
            return  new TextureRegion(atlas.findRegion(TILE, 50));
        }
        // legg til mer ettersom mer tile klasser blir lagt
        return null;
    }

}