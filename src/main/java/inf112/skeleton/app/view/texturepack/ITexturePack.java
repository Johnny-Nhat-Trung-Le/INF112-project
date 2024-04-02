package inf112.skeleton.app.view.texturepack;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.model.PlayerState;
import inf112.skeleton.app.view.ViewableEffect;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.ViewableTile;

public interface ITexturePack {
    /**
     * Returns a {@link TextureRegion} based on the specified {@link TileModel}.
     *
     * @param tile that is used for selecting the {@link TextureRegion}
     * @return {@link TextureRegion} based on the {@link TileModel}
     */
    TextureRegion getTileTexture(ViewableTile tile);

    /**
     * Returns a {@link TextureRegion} based on the player's
     * {@link PlayerState} and the model's {@code stateTime}.
     *
     * @param state the {@link PlayerState} the player currently is in
     * @return the {@link TextureRegion} for the given {@link PlayerState}
     */
    TextureRegion getPlayerTexture(PlayerState state, float stateTime);

    /**
     * Returns a {@link TextureRegion} based on class of the {@link ViewableItem}.
     * @param item that is used to get the {@link TextureRegion}
     * @return the {@link TextureRegion} for the given {@link ViewableItem}
     */
    TextureRegion getItemTexture(ViewableItem item);

    /**
     * @return a {@link TextureRegion} of the inventory slot
     */
    TextureRegion getInventorySlot();
}
