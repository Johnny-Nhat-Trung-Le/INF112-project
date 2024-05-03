package inf112.lilBro.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameOverScreen extends AbstractScreen {
    private final Texture texture = new Texture("Backgrounds/background_001.png");
    private final GlyphLayout layout;
    private final String title = "Game Over !_!";
    private final String text = "Press R to restart!";

    /**
     * Creates a screen for when the gamestate is Game Over
     *
     * @param processor input processor
     */
    public GameOverScreen(InputProcessor processor) {
        super(processor);
        layout = new GlyphLayout();
        layout.setText(font, title);
        layout.setText(font, text);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        font.draw(batch, title, (VIEWPORT_WIDTH / 2) - layout.width / 2, (VIEWPORT_HEIGHT / 4) * 3 - (layout.height / 2));
        font.draw(batch, text, (VIEWPORT_WIDTH / 2) - layout.width / 2, ((VIEWPORT_HEIGHT / 4) * 2) - layout.height / 2);
        batch.end();
    }
}
