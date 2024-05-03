package inf112.lilBro.app.view.texturepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.lilBro.app.model.PlayerState;
import inf112.lilBro.app.model.effect.EffectJumpBoost;
import inf112.lilBro.app.model.effect.EffectSpeedBoost;
import inf112.lilBro.app.model.item.ItemEnergy;
import inf112.lilBro.app.model.item.ItemHP;
import inf112.lilBro.app.model.item.ItemMushroom;
import inf112.lilBro.app.model.tiles.*;
import inf112.lilBro.app.model.tiles.contactableTiles.Door1;
import inf112.lilBro.app.model.tiles.contactableTiles.Door2;
import inf112.lilBro.app.model.tiles.contactableTiles.Saw;
import inf112.lilBro.app.model.tiles.contactableTiles.Spike;
import inf112.lilBro.app.view.ViewableEffect;
import inf112.lilBro.app.view.ViewableItem;
import inf112.lilBro.app.view.ViewableTile;

import java.util.HashMap;
import java.util.Map;

public class TexturePack implements ITexturePack {
    public static final String TILE_NAME = "tile";
    private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/gameAtlas.atlas"));
    private final static int TILE_WIDTH = 32;
    private final static int TILE_HEIGHT = 32;
    private final static Map<PlayerState, Animation<TextureRegion>> PLAYER_ANIMATION_MAP;

    static {
        PLAYER_ANIMATION_MAP = new HashMap<PlayerState, Animation<TextureRegion>>() {{
            put(PlayerState.JUMP_RIGHT, loadAnimation("Pink_Monster/Pink_Monster_Jump_6.png", 2));
            put(PlayerState.JUMP_LEFT, loadAnimation("Pink_Monster/Pink_Monster_LeftJump_6.png", 2));
            put(PlayerState.LEFT, loadAnimation("Pink_Monster/Pink_Monster_LeftRun_6.png", 6));
            put(PlayerState.RIGHT, loadAnimation("Pink_Monster/Pink_Monster_Run_6.png", 6));
            put(PlayerState.IDLE_RIGHT, loadAnimation("Pink_Monster/Pink_Monster_Idle_4.png", 4));
            put(PlayerState.IDLE_LEFT, loadAnimation("Pink_Monster/Pink_Monster_Idle_Left_4.png", 4));
        }};
    }

    private float playerStateTime;
    private PlayerState playerLastState;

    /**
     * Get the specific Texture for the Animation it should be in
     *
     * @param imgSrc the img source for the animation player is in
     * @param frames how many frames there is in that spriteSheet
     * @return Animation<TextureRegion> for the given Animation
     */
    private static Animation<TextureRegion> loadAnimation(String imgSrc, int frames) {
        Texture img = new Texture(imgSrc);

        //frames represent the amount of picture an animation sequence have
        TextureRegion[][] tmpFrames = TextureRegion.split(img, TILE_WIDTH, TILE_HEIGHT);
        TextureRegion[] animationFrames = new TextureRegion[frames];
        int index = 0;
        for (int i = 0; i < frames; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }
        return new Animation<TextureRegion>(1f / 16f, animationFrames);
    }

    /**
     * Get the specific texture from the textureAtlas
     *
     * @param index the index of the specific texture in the textureAtlas
     * @return TextureRegion from the textureAtlas
     */
    private TextureRegion getTextureFromAtlas(int index) {
        TextureAtlas.AtlasRegion ar = atlas.findRegion(TILE_NAME, index);
        TextureRegion tr = new TextureRegion(ar);
        switch (index) {
            case 46: // Spike
                // 14x13
                tr.setRegionX(tr.getRegionX() + (16 - 14) / 2);
                tr.setRegionWidth(14);
                tr.setRegionY(tr.getRegionY() + 16 - 13);
                tr.setRegionHeight(13);
                break;
            case 47: // Saw
                // 16x8
                tr.setRegionY(tr.getRegionY() + 16 - 8);
                tr.setRegionHeight(8);
                break;
        }
        return tr;
    }

    @Override
    public TextureRegion getTileTexture(ViewableTile tile) {
        if (tile instanceof TileGroundSingle) return getTextureFromAtlas(0);
        if (tile instanceof TileGround) return getTextureFromAtlas(2);
        if (tile instanceof TileGroundLeft) return getTextureFromAtlas(1);
        if (tile instanceof TileGroundRight) return getTextureFromAtlas(3);

        if (tile instanceof TileGrassCornerRight1) return getTextureFromAtlas(4);
        if (tile instanceof TileGrassCornerLeft1) return getTextureFromAtlas(6);
        if (tile instanceof TileGrassCornerRight2) return getTextureFromAtlas(20);
        if (tile instanceof TileGrassCornerLeft2) return getTextureFromAtlas(22);

        if (tile instanceof TileSideLeft) return getTextureFromAtlas(17);
        if (tile instanceof TileMiddle) return getTextureFromAtlas(18);
        if (tile instanceof TileSideRight) return getTextureFromAtlas(19);
        if (tile instanceof TileBottomLeft) return getTextureFromAtlas(33);
        if (tile instanceof TileBottom) return getTextureFromAtlas(34);
        if (tile instanceof TileBottomRight) return getTextureFromAtlas(35);

        if (tile instanceof TilePillar) return getTextureFromAtlas(16);
        if (tile instanceof TilePillarBottom) return getTextureFromAtlas(32);

        if (tile instanceof TileFloatingGroundSingle) return getTextureFromAtlas(48);
        if (tile instanceof TileFloatingGround) return getTextureFromAtlas(50);
        if (tile instanceof TileFloatingGroundLeft) return getTextureFromAtlas(49);
        if (tile instanceof TileFloatingGroundRight) return getTextureFromAtlas(51);

        if (tile instanceof TileFloatingGroundSingleSlim) return getTextureFromAtlas(64);
        if (tile instanceof TileFloatingGroundSlim) return getTextureFromAtlas(66);
        if (tile instanceof TileFloatingGroundLeftSlim) return getTextureFromAtlas(65);
        if (tile instanceof TileFloatingGroundRightSlim) return getTextureFromAtlas(67);

        if (tile instanceof Barrel) return getTextureFromAtlas(55);
        if (tile instanceof Spike) return getTextureFromAtlas(46);
        if (tile instanceof Saw) return getTextureFromAtlas(45);
        if (tile instanceof Door1) return getTextureFromAtlas(7);
        if (tile instanceof Door2) return getTextureFromAtlas(23);

        return null;
    }

    @Override
    public TextureRegion getPlayerTexture(PlayerState state, float stateTime) {
        updatePlayerVariables(state, stateTime);
        boolean looping = !state.equals(PlayerState.JUMP_RIGHT) && !state.equals(PlayerState.JUMP_LEFT);
        return PLAYER_ANIMATION_MAP.get(state).getKeyFrame(this.playerStateTime, looping);

    }

    private void updatePlayerVariables(PlayerState state, float stateTime) {
        if (state.equals(playerLastState)
                || PlayerState.JUMP_RIGHT.equals(playerLastState) && PlayerState.JUMP_LEFT.equals(state)
                || PlayerState.JUMP_LEFT.equals(playerLastState) && PlayerState.JUMP_RIGHT.equals(state)) {
            playerStateTime += stateTime;
        } else {
            playerStateTime = 0;
        }
        playerLastState = state;
    }

    @Override
    public TextureRegion getItemTexture(ViewableItem item) {

        if (item instanceof ItemEnergy) return getTextureFromAtlas(156);
        if (item instanceof ItemMushroom) return getTextureFromAtlas(106);
        if (item instanceof ItemHP) return getTextureFromAtlas(139);
        return null;
    }

    @Override
    public TextureRegion getHpTexture() {
        return getTextureFromAtlas(139);
    }

    @Override
    public TextureRegion getEffectTexture(ViewableEffect item) {
        if (item instanceof EffectSpeedBoost) return getTextureFromAtlas(156);
        if (item instanceof EffectJumpBoost) return getTextureFromAtlas(106);
        return null;
    }

    @Override
    public TextureRegion getInventorySlot() {
        return atlas.findRegion(TILE_NAME, 85);
        // 85,87,101,117,144,148
    }
}