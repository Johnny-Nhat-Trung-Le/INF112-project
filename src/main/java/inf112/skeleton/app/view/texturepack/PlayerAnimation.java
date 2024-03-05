package inf112.skeleton.app.view.texturepack;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import inf112.skeleton.app.model.PlayerState;
import inf112.skeleton.app.view.ViewableTile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static javax.swing.UIManager.put;

public class PlayerAnimation implements ITexturePack {
    private final static int tileWidth = 32;
    private final static int tileHeight = 32;
    private final static Map<PlayerState,Animation<TextureRegion>> animationMap;

    static{
        animationMap = new HashMap<PlayerState, Animation<TextureRegion>>(){
            {
                put(PlayerState.DOWN,loadAnimation("Pink_Monster/Pink_Monster_Climb_4.png",4));
                put(PlayerState.JUMP_RIGHT,loadAnimation("Pink_Monster/Pink_Monster_Jump_8.png",8));
                put(PlayerState.JUMP_LEFT,loadAnimation("Pink_Monster/Pink_Monster_LeftJump_8.png",8));
                put(PlayerState.LEFT,loadAnimation("Pink_Monster/Pink_Monster_LeftRun_6.png",6));
                put(PlayerState.RIGHT, loadAnimation("Pink_Monster/Pink_Monster_Run_6.png",6));
                put(PlayerState.IDLE,loadAnimation("Pink_Monster/Pink_Monster_Idle_4.png",4));
            }
        };

    }

    /**
     * get the animation for the given playerState
     * @param playerState the playerState the player currently is in
     * @return The correct Animation<TextureRegion> for the given PlayerState
     */
    public static Animation<TextureRegion> getAnimation(PlayerState playerState){
        return animationMap.get(playerState);
    }

    /**
     * Get the specific Texture for the Animation it should be in
     * @param imgSrc the img source for the animation player is in
     * @param frames how many frames there is in that spriteSheet
     * @return Animation<TextureRegion> for the given Animation
     */
    private static Animation<TextureRegion> loadAnimation(String imgSrc, int frames){
        Texture img = new Texture(imgSrc);

        //frames represent the amount of picture an animation sequence have
        TextureRegion[][] tmpFrames = TextureRegion.split(img,tileWidth,tileHeight);
        TextureRegion[] animationFrames = new TextureRegion[frames];
        int index = 0;
        for (int i = 0; i < frames; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }
        return  new Animation<TextureRegion>(1f/16f,animationFrames);
    };

    @Override
    public TextureRegion getTileTexture(ViewableTile tile) {
        return null;
    }


}
