package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.view.GameView;
import inf112.skeleton.app.view.ViewableGameModel;

public class MenuScreen implements Screen {
    private final SpriteBatch batch;
    private static float ViewPort = 400;
    private static float VIEWPORT_WIDTH;
    private static float VIEWPORT_HEIGHT;
    private final OrthographicCamera gameCam;
    private final FillViewport gamePort;
    private final Texture texture;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private final String text;

    public MenuScreen(InputProcessor processor) {
        batch = new SpriteBatch();
        VIEWPORT_HEIGHT = ViewPort / GameView.ASPECT_RATIO;
        VIEWPORT_WIDTH = GameView.ASPECT_RATIO * ViewPort;

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCam);

        texture = new Texture("Backgrounds/background_001.png");
        font = new BitmapFont();
        text = "welcome press p to play";
        layout = new GlyphLayout();
        layout.setText(font, text);
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
        font.draw(batch, text, (VIEWPORT_WIDTH / 2) - layout.width / 2, (VIEWPORT_HEIGHT / 2) - layout.height / 2);
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
