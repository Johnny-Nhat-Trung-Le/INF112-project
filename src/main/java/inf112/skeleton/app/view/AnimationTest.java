package inf112.skeleton.app.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import org.lwjgl.opengl.GL20;

public class AnimationTest extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture img;
    private TextureRegion[] animationFrames;
    private Animation<TextureRegion> animation;
    private float elapsedTime;

    @Override
    public void create() {

        this.batch = new SpriteBatch();
        this.img = new Texture("Pink_Monster/Pink_Monster_Jump_8.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(this.img, 32, 32);
        this.animationFrames = new TextureRegion[2];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            this.animationFrames[index++] = tmpFrames[0][i];
        }
        this.animation = new Animation<TextureRegion>(1f/8f, animationFrames);
        Gdx.graphics.setForegroundFPS(60);
        ScreenUtils.clear(Color.WHITE);



    }
    @Override
    public void dispose(){
        this.batch.dispose();
    }
    @Override
    public void render(){

        this.elapsedTime+= Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(animation.getKeyFrame(this.elapsedTime,true),0,0, 80, 80);
        batch.end();

    }
}
