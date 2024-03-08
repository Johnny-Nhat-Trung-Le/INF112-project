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
import com.badlogic.gdx.utils.viewport.FillViewport;
import inf112.skeleton.app.view.GameView;

public class GameOverScreen implements Screen {
    private final SpriteBatch batch;
    private static float VIEWPORT_WIDTH;
    private static float VIEWPORT_HEIGHT;
    private static float ViewPort = 400;
    private final OrthographicCamera gameCam;
    private final FillViewport gamePort;
    private final Texture texture;
    private final BitmapFont font;
    private final GlyphLayout layout;

    public GameOverScreen(InputProcessor processor) {
        batch = new SpriteBatch();
        VIEWPORT_HEIGHT = ViewPort / GameView.ASPECT_RATIO;
        VIEWPORT_WIDTH = GameView.ASPECT_RATIO * ViewPort;

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCam);

        texture = new Texture("Backgrounds/background_001.png");
        font = new BitmapFont();
        layout = new GlyphLayout();
        layout.setText(font, "Game Over");
        layout.setText(font, "Press R to restart");

        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setInputProcessor(processor);

    }

    private void update() {
        gameCam.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
        batch.setProjectionMatrix(gameCam.combined);
        gameCam.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(0, 0, 0, 0);
        batch.begin();
        //Fills the whole screen
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        font.draw(batch, "GAME OVER", (VIEWPORT_WIDTH / 2) - layout.width / 2, (VIEWPORT_HEIGHT / 4) * 3 - (layout.height / 2));
        font.draw(batch, "Press R to restart", (VIEWPORT_WIDTH / 2) - layout.width / 2, ((VIEWPORT_HEIGHT / 4) * 2) - layout.height / 2);
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

    }

    @Override
    public void dispose() {

    }
}
