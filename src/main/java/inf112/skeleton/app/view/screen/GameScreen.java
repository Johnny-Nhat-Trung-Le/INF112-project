package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import inf112.skeleton.app.view.texturepack.ITexturePack;
import inf112.skeleton.app.view.texturepack.PlayerAnimation;
import inf112.skeleton.app.view.texturepack.TexturePack;

//some imports
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

    // Testing
    private final ITexturePack texturePack;

    public GameScreen(ViewableGameModel model, EventBus bus, InputProcessor processor) {
        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setInputProcessor(processor);

        this.model = model;
        eventBus = bus;

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCam);

        sRenderer = new ShapeRenderer();

        //Here
        this.texturePack = new TexturePack();


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


//        this.batch.draw(texturePack.getTexture("tile_0"),0,0,16,16);
//        this.batch.draw(texturePack.test(),0,0,16,16);
        // Draws the player
        this.batch.draw(
                PlayerAnimation.getAnimation(player.getPlayerState()).getKeyFrame(dt, true),
                player.getX(),
                player.getY(),
                player.getWidth(),
                player.getHeight()
        );
        this.batch.end();
    }

    private void updateCamToPlayer() {
        ViewablePlayerModel p = model.getViewablePlayer();
        gameCam.position.set(p.getX() + p.getWidth() / 2, p.getY() + p.getHeight() / 2, 0);
        sRenderer.setProjectionMatrix(gameCam.combined);
        batch.setProjectionMatrix(gameCam.combined);
        gameCam.update();
    }

    private void renderBackground() {
        sRenderer.setColor(Color.BLACK);
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
        TextureRegion tileTexture = texturePack.getTileTexture(tile);

        sRenderer.setColor(Color.BLUE);
        sRenderer.rect(tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
        // Testing
        if (tileTexture != null) {
            System.out.println("meow meow n");
            // I`WHY DOESNT INT FUCKGIN DRAW THIS RETARDED PIREFE OF ODGSHIT
            // Idiont se
            batch.begin();
            batch.draw(tileTexture, tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
            batch.end();
        } else {
            // fuck my life
            System.out.println("Texture not found muddafukka raaahhhh" + tile.getTextureKey());
        }

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

