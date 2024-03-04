package inf112.skeleton.app.view.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.view.ViewableGameModel;

public class MenuScreen implements Screen{
    private final SpriteBatch batch;
    private static float VIEWPORT_WIDTH ;
    private static float VIEWPORT_HEIGHT;
    private final OrthographicCamera gameCam;
    private final ScreenViewport gamePort;
    private final Texture texture;

    public MenuScreen(InputProcessor processor){
        batch = new SpriteBatch();
        VIEWPORT_HEIGHT = Gdx.graphics.getHeight();
        VIEWPORT_WIDTH = Gdx.graphics.getWidth();

        gameCam = new OrthographicCamera();
        gamePort = new ScreenViewport(gameCam);

        texture = new Texture("Backgrounds/background_003.png");

        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setInputProcessor(processor);

    }

    @Override
    public void show() {
        gameCam.zoom = 2f;
    }
    private void update(){
        batch.setProjectionMatrix(gameCam.combined);
        gameCam.update();
    }
    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(0, 0, 0, 0);

        batch.begin();
        //Fills the whole screen
        batch.draw(texture,0,0,VIEWPORT_WIDTH,VIEWPORT_HEIGHT);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height,true);
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
