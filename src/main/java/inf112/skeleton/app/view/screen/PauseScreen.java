package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class PauseScreen implements Screen {

    private final SpriteBatch batch;
    private static float VIEWPORT_WIDTH ;
    private static float VIEWPORT_HEIGHT;
    private final OrthographicCamera gameCam;
    private final FillViewport gamePort;
    private final Texture texture;
    private final BitmapFont font;
    public PauseScreen(InputProcessor processor){
        batch = new SpriteBatch();
        VIEWPORT_HEIGHT = Gdx.graphics.getHeight();
        VIEWPORT_WIDTH = Gdx.graphics.getWidth();

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(VIEWPORT_WIDTH,VIEWPORT_HEIGHT,gameCam);
        //FIXME burde lage en egen png for det
        texture = new Texture("Backgrounds/background_005.png");
        font = new BitmapFont();

        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setInputProcessor(processor);
    }

    @Override
    public void show() {

    }
    private void update(){
        gameCam.position.set(VIEWPORT_WIDTH/2, VIEWPORT_HEIGHT / 2, 0);
        batch.setProjectionMatrix(gameCam.combined);
        gameCam.update();
    }
    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(0, 0, 0, 0);
        batch.begin();
        //FIXME tegne riktig PauseScreen
        batch.draw(texture,0,0,VIEWPORT_WIDTH,VIEWPORT_HEIGHT);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height,true);
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
