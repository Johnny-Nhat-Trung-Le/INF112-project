package inf112.lilBro.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.lilBro.app.view.GameView;

public abstract class AbstractScreen implements Screen {
    protected static final BitmapFont font = new BitmapFont();
    private static final float ViewPort = 400;
    public static float VIEWPORT_WIDTH = GameView.ASPECT_RATIO * 175;
    public static float VIEWPORT_HEIGHT = (float) (ViewPort / GameView.ASPECT_RATIO * 1.2);
    protected final SpriteBatch batch;
    protected final OrthographicCamera gameCam;
    private final Viewport gamePort;

    /**
     * An abstract class for all the screens
     *
     * @param processor the input processor
     */
    public AbstractScreen(InputProcessor processor) {
        batch = new SpriteBatch();

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCam);
        font.setColor(Color.WHITE);
        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setInputProcessor(processor);
    }


    @Override
    public void show() {
    }

    /**
     * Updates all the calculations necessary for each render
     */
    private void update() {
        gameCam.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
        batch.setProjectionMatrix(gameCam.combined);
        gameCam.update();
    }

    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(0, 0, 0, 0);
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
