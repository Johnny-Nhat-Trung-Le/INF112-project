package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;

public class InfoScreen extends AbstractScreen {

    private static final Texture texture = new Texture("Backgrounds/openBook.png");
    public InfoScreen(InputProcessor processor) {
        super(processor);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        batch.end();
    }
}
