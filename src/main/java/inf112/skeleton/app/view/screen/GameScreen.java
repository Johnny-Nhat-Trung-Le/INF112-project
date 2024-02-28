package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.event.EventDispose;
import inf112.skeleton.app.model.event.EventStep;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;

public class GameScreen implements Screen {
    private static final float ASPECT_RATIO = 1.5f;
    private final EventBus eventBus;
    private final ShapeRenderer sRenderer;
    private final GameModel model;
    private final OrthographicCamera gameCam;
    private final Viewport gamePort;

    public GameScreen(GameModel model, EventBus bus, InputProcessor processor) {
        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setInputProcessor(processor);

        this.model = model;
        eventBus = bus;
        sRenderer = new ShapeRenderer();

        gameCam = new OrthographicCamera();
        gameCam.zoom = 1f;

        gamePort = new FillViewport(model.getWidth(), model.getWidth() / ASPECT_RATIO, gameCam);

        gameCam.update();

    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        eventBus.post(new EventStep(delta));
        updateCamToPlayer();

        ScreenUtils.clear(0,0,0,0);

        sRenderer.setProjectionMatrix(gameCam.combined);
        sRenderer.begin(ShapeRenderer.ShapeType.Filled);

        renderBackground();
        renderWorld();
        renderTiles();
        renderPlayer();

        sRenderer.end();
    }

    private void updateCamToPlayer() {
        ViewablePlayerModel p = model.getViewablePlayer();
        gameCam.position.set(p.getX() + p.getWidth() / 2, p.getY() + p.getHeight() / 2, 0);
        gameCam.update();
    }

    private void renderBackground() {
        sRenderer.setColor(Color.TEAL);
        sRenderer.rect(-model.getWidth(), -model.getHeight(), model.getWidth() * 3, model.getHeight() * 3);
    }

    private void renderWorld() {
        sRenderer.setColor(Color.BROWN);
        sRenderer.rect(0,0, model.getWidth(), model.getHeight());
    }

    private void renderTiles() {
        for (ViewableTile tile : model.getForegroundTiles()) {
            renderTile(tile);
        }
    }

    private void renderTile(ViewableTile tile) {
        sRenderer.setColor(Color.BLUE);
        sRenderer.rect(tile.getX(), tile.getY(), tile.getHeight(), tile.getWidth());
    }

    private void renderPlayer() {
        ViewablePlayerModel player = model.getViewablePlayer();

        sRenderer.setColor(Color.ORANGE);
        sRenderer.rect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void dispose() {
        sRenderer.dispose();
        eventBus.post(new EventDispose());
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

}

