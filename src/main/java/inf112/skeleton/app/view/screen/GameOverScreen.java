package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameOverScreen extends AbstractScreen {
    private final Texture texture;
    private final GlyphLayout layout;

    public GameOverScreen(InputProcessor processor) {
        super(processor);

        texture = new Texture("Backgrounds/background_001.png");
        layout = new GlyphLayout();
        layout.setText(font, "Game Over");
        layout.setText(font, "Press R to restart");

        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setInputProcessor(processor);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        //Fills the whole screen
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        font.draw(batch, "GAME OVER", (VIEWPORT_WIDTH / 2) - layout.width / 2, (VIEWPORT_HEIGHT / 4) * 3 - (layout.height / 2));
        font.draw(batch, "Press R to restart", (VIEWPORT_WIDTH / 2) - layout.width / 2, ((VIEWPORT_HEIGHT / 4) * 2) - layout.height / 2);
        batch.end();
    }
}
