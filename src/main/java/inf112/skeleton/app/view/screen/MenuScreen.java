package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MenuScreen extends AbstractScreen {
    private static final float TEXTURE_RATIO = 640 / (float) 352;
    private static final Texture texture = new Texture("Backgrounds/map.png"); //640*352
    private static final String title = "Lil bro's Adventure Back Home";
    private static final String text = "Press P to play";
    private static final GlyphLayout titleLayout = new GlyphLayout(font, title);
    private static final GlyphLayout textLayout = new GlyphLayout(font, text);

    public MenuScreen(InputProcessor processor) {
        super(processor);
        font.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        //Fills the whole screen
        batch.draw(texture, 0, 0, VIEWPORT_HEIGHT * TEXTURE_RATIO, VIEWPORT_HEIGHT);
        //Draws the texts
        font.draw(batch, title, (VIEWPORT_WIDTH / 2) - titleLayout.width / 2, (VIEWPORT_HEIGHT / 4) * 3);
        font.draw(batch, text, (VIEWPORT_WIDTH / 2) - textLayout.width / 2, (VIEWPORT_HEIGHT / 2) - textLayout.height / 2);
        batch.end();
    }
}
