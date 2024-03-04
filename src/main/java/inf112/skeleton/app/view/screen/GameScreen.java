package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.EventDispose;
import inf112.skeleton.app.model.event.EventStep;
import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;
import inf112.skeleton.app.view.texturepack.PlayerAnimation;

public class GameScreen implements Screen {
    private static final float VIEWPORT_WIDTH = 20;
    private static final float VIEWPORT_HEIGHT = 20;
    private final EventBus eventBus;
    private final ShapeRenderer sRenderer;
    private final ViewableGameModel model;
    private final OrthographicCamera gameCam;
    private final Viewport gamePort;
    private final SpriteBatch batch;
    private float dt;

    public GameScreen(ViewableGameModel model, EventBus bus, InputProcessor processor) {
        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setInputProcessor(processor);

        this.model = model;
        eventBus = bus;

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCam);

        sRenderer = new ShapeRenderer();

        batch = new SpriteBatch();
        dt = 0;
    }

    @Override
    public void show() {
        gameCam.zoom = 2f;
        updateCamToPlayer();
    }

    @Override
    public void render(float delta) {
        eventBus.post(new EventStep(delta));
        updateCamToPlayer();

        ViewablePlayerModel player = model.getViewablePlayer();

        ScreenUtils.clear(0, 0, 0, 0);

        sRenderer.begin(ShapeRenderer.ShapeType.Filled);

        renderBackground();
        renderWorld();
        renderTiles();
        renderPlayer();

        sRenderer.end();

        this.dt += Gdx.graphics.getDeltaTime();
        this.batch.begin();
        // Draws the player
//        this.batch.draw(
//                PlayerAnimation.getAnimation(player.getPlayerState()).getKeyFrame(dt, true),

        // Draws the player
        this.batch.draw(
                (PlayerAnimation.getAnimation(delta)),
                player.getX(),
                player.getY(),
                player.getWidth(),
                player.getHeight()
        );
        this.batch.end();

        //object.render();
    }

    private void updateCamToPlayer() {
        ViewablePlayerModel p = model.getViewablePlayer();
        gameCam.position.set(p.getX() + p.getWidth() / 2, p.getY() + p.getHeight() / 2, 0);
        sRenderer.setProjectionMatrix(gameCam.combined);
        batch.setProjectionMatrix(gameCam.combined);
        gameCam.update();
    }

    private void renderBackground() {
        sRenderer.setColor(Color.TEAL);
        sRenderer.rect(-VIEWPORT_WIDTH, -VIEWPORT_HEIGHT, VIEWPORT_WIDTH * 3, VIEWPORT_HEIGHT * 3);
    }

    private void renderWorld() {
        sRenderer.setColor(Color.BROWN);
        sRenderer.rect(0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    private void renderTiles() {
        for (ViewableTile tile : model.getForegroundTiles()) {
            renderTile(tile);
        }
    }

    private void renderTile(ViewableTile tile) {
        sRenderer.setColor(Color.BLUE);
        sRenderer.rect(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
    }

    private void renderPlayer() {
        ViewablePlayerModel player = model.getViewablePlayer();

        sRenderer.setColor(Color.ORANGE);
        sRenderer.rect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void dispose() {
        sRenderer.dispose();
        batch.dispose();
        eventBus.post(new EventDispose());
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

}

