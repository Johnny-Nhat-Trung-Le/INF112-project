package inf112.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Background extends Actor {
    private final Array<Texture> layers;
    private final float x, y, width, height;

    public Background(Array<Texture> textures) {
        layers = textures;
        x = y = 0;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        for (int i = 0; i < layers.size; i++) {
            batch.draw(layers.get(i),x,y,width,height);
        }
    }
}