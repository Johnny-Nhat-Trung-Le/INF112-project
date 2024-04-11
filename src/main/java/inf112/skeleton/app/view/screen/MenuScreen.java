package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MenuScreen extends AbstractScreen {


    private final Texture texture;
    //943*689
    private final GlyphLayout textLayout;

    private  final GlyphLayout titleLayout;
    private  final String text;
    private  final String title;

    public MenuScreen(InputProcessor processor) {
        super(processor);
        texture = new Texture("Backgrounds/menu_screen.png");
        title = "Lil bro's Adventure Back Home";
        text = "Press p to play";
        textLayout = new GlyphLayout();
        titleLayout = new GlyphLayout();
        titleLayout.setText(font, title);
        textLayout.setText(font, text);
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        //Fills the whole screen
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        //Draws the texts
        font.draw(batch, title, (VIEWPORT_WIDTH / 2) - titleLayout.width / 2, (VIEWPORT_HEIGHT / 4) * 3);
        font.draw(batch, text, (VIEWPORT_WIDTH / 2) - textLayout.width / 2, (VIEWPORT_HEIGHT / 2) - textLayout.height / 2);
        batch.end();
    }



}
