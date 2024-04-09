package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import inf112.skeleton.app.view.GameView;

public class MenuScreen implements Screen {
    private final SpriteBatch batch;
    private static final float ViewPort = 400;
    private static float VIEWPORT_WIDTH;
    private static float VIEWPORT_HEIGHT;
    private final OrthographicCamera gameCam;
    private final Viewport gamePort;
    private final Texture texture;
    //943*689
    private final BitmapFont font;
    private final GlyphLayout textLayout;

    private final GlyphLayout titleLayout;
    private final String text;
    private final String title;

    public MenuScreen(InputProcessor processor) {
        batch = new SpriteBatch();
        VIEWPORT_HEIGHT = (float) (ViewPort / GameView.ASPECT_RATIO * 1.2);
        VIEWPORT_WIDTH = GameView.ASPECT_RATIO * 175;

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCam);
        texture = new Texture("Backgrounds/menu_screen.png");
        font = new BitmapFont();

        title = "Lil bro's Adventure Back Home";
        text = "Press p to play";
        textLayout = new GlyphLayout();
        titleLayout = new GlyphLayout();
        titleLayout.setText(font, title);
        textLayout.setText(font, text);

        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setInputProcessor(processor);

    }

    @Override
    public void show() {

    }

    private void update() {
        gameCam.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
        batch.setProjectionMatrix(gameCam.combined);
        gameCam.update();
    }

    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(0, 0, 0, 0);

        batch.begin();
        //Fills the whole screen
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        //Draws the texts
        font.draw(batch, title, (VIEWPORT_WIDTH / 2) - titleLayout.width / 2, (VIEWPORT_HEIGHT / 4) * 3);
        font.draw(batch, text, (VIEWPORT_WIDTH / 2) - textLayout.width / 2, (VIEWPORT_HEIGHT / 2) - textLayout.height / 2);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height, true);
        gameCam.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }


}
