package inf112.skeleton.app.view.texturepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.model.PlayerState;
import inf112.skeleton.app.model.tiles.*;
import inf112.skeleton.app.model.tiles.contactableTiles.Spike;
import inf112.skeleton.app.view.ViewableTile;

import java.util.HashMap;
import java.util.Map;

public class TexturePack implements ITexturePack {
    private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/gameAtlas.atlas"));
    public static final String TILE_NAME = "tile";
    private final static int TILE_WIDTH = 32;
    private final static int TILE_HEIGHT = 32;
    private final static Map<PlayerState, Animation<TextureRegion>> PLAYER_ANIMATION_MAP;
    private float playerStateTime;
    private PlayerState playerLastState;

    static {
        PLAYER_ANIMATION_MAP = new HashMap<PlayerState, Animation<TextureRegion>>() {{
            put(PlayerState.DOWN, loadAnimation("Pink_Monster/Pink_Monster_Climb_4.png", 4));
            put(PlayerState.JUMP_RIGHT, loadAnimation("Pink_Monster/Pink_Monster_Jump_8.png", 8));
            put(PlayerState.JUMP_LEFT, loadAnimation("Pink_Monster/Pink_Monster_LeftJump_8.png", 8));
            put(PlayerState.LEFT, loadAnimation("Pink_Monster/Pink_Monster_LeftRun_6.png", 6));
            put(PlayerState.RIGHT, loadAnimation("Pink_Monster/Pink_Monster_Run_6.png", 6));
            put(PlayerState.IDLE_RIGHT, loadAnimation("Pink_Monster/Pink_Monster_Idle_4.png", 4));
            put(PlayerState.IDLE_LEFT, loadAnimation("Pink_Monster/Pink_Monster_Idle_Left_4.png", 4));
        }};
    }

    @Override
    public TextureRegion getTileTexture(ViewableTile tile) {
        if (tile instanceof TileGroundSingle) return new TextureRegion(atlas.findRegion(TILE_NAME,0));
        if (tile instanceof TileGround) return new TextureRegion(atlas.findRegion(TILE_NAME, 2));
        if (tile instanceof TileGroundLeft) return new TextureRegion(atlas.findRegion(TILE_NAME, 1));
        if (tile instanceof TileGroundRight) return new TextureRegion(atlas.findRegion(TILE_NAME, 3));

        if (tile instanceof TileFloatingGroundSingle) return new TextureRegion(atlas.findRegion(TILE_NAME,48));
        if (tile instanceof TileFloatingGround) return new TextureRegion(atlas.findRegion(TILE_NAME, 50));
        if (tile instanceof TileFloatingGroundLeft) return new TextureRegion(atlas.findRegion(TILE_NAME, 49));
        if (tile instanceof TileFloatingGroundRight) return new TextureRegion(atlas.findRegion(TILE_NAME, 51));

        if (tile instanceof TileFloatingGroundSingleSlim) return new TextureRegion(atlas.findRegion(TILE_NAME,64));
        if (tile instanceof TileFloatingGroundSlim) return new TextureRegion(atlas.findRegion(TILE_NAME, 66));
        if (tile instanceof TileFloatingGroundLeftSlim) return new TextureRegion(atlas.findRegion(TILE_NAME, 65));
        if (tile instanceof TileFloatingGroundRightSlim) return new TextureRegion(atlas.findRegion(TILE_NAME, 67));

        if (tile instanceof Barrel) return new TextureRegion(atlas.findRegion(TILE_NAME, 55));
        if (tile instanceof Spike) return  new TextureRegion(atlas.findRegion(TILE_NAME,46));

        return null;
    }

    @Override
    public TextureRegion getPlayerTexture(PlayerState state, float stateTime) {
        updatePlayerVariables(state, stateTime);
        return PLAYER_ANIMATION_MAP.get(state).getKeyFrame(this.playerStateTime, true);
    }

    private void updatePlayerVariables(PlayerState state, float stateTime) {
        if (state.equals(playerLastState)) {
            playerStateTime += stateTime;
        } else {
            playerStateTime = 0;
        }
        playerLastState = state;
    }

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

    ;
}