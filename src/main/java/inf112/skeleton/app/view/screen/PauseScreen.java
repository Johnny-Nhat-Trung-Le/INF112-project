package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class PauseScreen extends AbstractScreen {
    private final Texture texture;
    private final GlyphLayout layout;
    private final String text;

    public PauseScreen(InputProcessor processor) {
        super(processor);
        texture = new Texture("Backgrounds/background_005.png");
        text = "Pause! Press P to continue";
        layout = new GlyphLayout();
        layout.setText(font, text);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        font.draw(batch, text, (VIEWPORT_WIDTH / 2) - layout.width / 2, (VIEWPORT_HEIGHT / 2) - layout.height / 2);
        batch.end();
    }
}
