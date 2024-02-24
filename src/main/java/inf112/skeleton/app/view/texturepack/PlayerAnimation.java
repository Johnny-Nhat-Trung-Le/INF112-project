package inf112.skeleton.app.view.texturepack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimation implements ITexturePack{

    private Texture img;
    private final int tileWidth = 32;

    private final int tileHeight = 32;
    private Animation<TextureRegion> animation;
    public Animation<TextureRegion> getLeftAnimation(){
        this.img = new Texture("Pink_Monster/Pink_Monster_Jump_8.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(this.img,this.tileWidth,this.tileHeight);
        TextureRegion[] animationFrames = new TextureRegion[8];
        int index = 0;
        for (int i = 0; i < 8; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }
        this.animation = new Animation<TextureRegion>(1f/8f,animationFrames);
        return this.animation;
    }
    public Animation getRightAnimation(){
            return null;
    }
}
