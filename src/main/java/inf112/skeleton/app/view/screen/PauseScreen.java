package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import inf112.skeleton.app.view.screen.AbstractScreen;

public class PauseScreen extends AbstractScreen {
    private final Texture texture = new Texture("Backgrounds/background_005.png");
    private final String title = "Pause!";
    private final String text ="Press P to continue\n\n  Press H for help";
    private final GlyphLayout titleLayout = new GlyphLayout(font,title);
    private final GlyphLayout textLayout = new GlyphLayout(font,text);


    public PauseScreen(InputProcessor processor) {
        super(processor);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        font.draw(batch, title, (VIEWPORT_WIDTH / 2) - titleLayout.width / 2, (VIEWPORT_HEIGHT / 4 * 3) - titleLayout.height / 2);
        font.draw(batch, text, (VIEWPORT_WIDTH / 2) - textLayout.width / 2, (VIEWPORT_HEIGHT / 4) * 2 - textLayout.height / 2);
        batch.end();
    }
}
