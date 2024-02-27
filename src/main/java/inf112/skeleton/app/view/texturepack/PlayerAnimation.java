package inf112.skeleton.app.view.texturepack;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import inf112.skeleton.app.model.PlayerState;
//TODO er det Actor dette objectet skal implementere??
public class PlayerAnimation implements ITexturePack {

    private Texture img;
    private final int tileWidth = 32;

    private final int tileHeight = 32;
    private Animation<TextureRegion> animation;



    public Animation<TextureRegion> getAnimation(PlayerState playerState){
        return switch (playerState) {
            case JUMP -> getJumpAnimation();
            case DOWN -> getDownAnimation();
            case LEFT -> getLeftAnimation();
            case RIGHT -> getRightAnimation();
            default -> getIdleAnimation();
        };


    }
    // Ha en private static metode som gjør alt
    private static Animation<TextureRegion> loadAnimation(Texture img, int frames){


    };
    private Animation<TextureRegion> getRightAnimation(){
        this.img = new Texture("Pink_Monster/Pink_Monster_Run_6.png");
        //frames represent the amount of picture an animation sequence have
        int frames = 6;
        TextureRegion[][] tmpFrames = TextureRegion.split(this.img,this.tileWidth,this.tileHeight);
        TextureRegion[] animationFrames = new TextureRegion[frames];
        int index = 0;
        for (int i = 0; i < frames; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }
        this.animation = new Animation<TextureRegion>(1f/16f,animationFrames);
        return this.animation;
    }
    private Animation<TextureRegion> getLeftAnimation(){
            //TODO FIKSE Left ANIMATION, VÅR TEXTUREPACK CURRENTLY HAR IKKE EN Left
            return null;
    }
    private Animation<TextureRegion> getJumpAnimation(){
        this.img = new Texture("Pink_Monster/Pink_Monster_Jump_8.png");
        //frames represent the amount of picture an animation sequence have
        int frames = 8;
        TextureRegion[][] tmpFrames = TextureRegion.split(this.img,this.tileWidth,this.tileHeight);
        TextureRegion[] animationFrames = new TextureRegion[frames];
        int index = 0;
        for (int i = 0; i < frames; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }
        this.animation = new Animation<TextureRegion>(1f/16f,animationFrames);
        return this.animation;
    }
    private Animation<TextureRegion> getDownAnimation(){
        this.img = new Texture("Pink_Monster/Pink_Monster_Climb_4.png");
        //frames represent the amount of picture an animation sequence have
        int frames = 4;
        TextureRegion[][] tmpFrames = TextureRegion.split(this.img,this.tileWidth,this.tileHeight);
        TextureRegion[] animationFrames = new TextureRegion[frames];
        int index = 0;
        for (int i = 0; i < frames; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }
        this.animation = new Animation<TextureRegion>(1f/16f,animationFrames);
        return this.animation;
    }
    private Animation<TextureRegion> getIdleAnimation(){
        this.img = new Texture("Pink_Monster/Pink_Monster_Idle_4.png");
        //frames represent the amount of picture an animation sequence have
        int frames = 4;
        TextureRegion[][] tmpFrames = TextureRegion.split(this.img,this.tileWidth,this.tileHeight);
        TextureRegion[] animationFrames = new TextureRegion[frames];

        int index = 0;
        for (int i = 0; i < frames; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }
        this.animation = new Animation<TextureRegion>(1f/16f,animationFrames);
        return this.animation;
    }
}
