package inf112.lilBro.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class VictoryScreen extends AbstractScreen {
    private final Texture texture = new Texture("Backgrounds/victoryScreen.png");
    private final String text = "You won the game!\nThank you for playing!\n Press B to go back to Main Menu\n Press ESC to quit";
    private final String title = "Victory";

    private final GlyphLayout textLayout = new GlyphLayout(font, text);
    private final GlyphLayout titleLayout = new GlyphLayout(font, title);

    /**
     * Creates the victory screen which should be displayed when player has won the level
     *
     * @param processor The input processor
     */
    public VictoryScreen(InputProcessor processor) {super(processor);}

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        font.draw(batch, title, (VIEWPORT_WIDTH / 2) - titleLayout.width / 2, (VIEWPORT_HEIGHT / 4) * 3);
        font.draw(batch, text, (VIEWPORT_WIDTH / 2) - textLayout.width / 2, (VIEWPORT_HEIGHT / 4) * 3 - textLayout.height / 2);
        batch.end();
    }
}

