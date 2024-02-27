package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.texturepack.PlayerAnimation;

public class GameScreen implements Screen {
    private ViewableGameModel model;
    private ViewablePlayerModel player;
    private OrthographicCamera gameCam;
    //ViewPort viser hvordan grafikken skal se ut mtp skjermen til enheten
    private Viewport gamePort;
    //private Hud hud;
    private SpriteBatch batch;
    private PlayerAnimation playerAnimation;
    private float dt;
    private World world;
    private Box2DDebugRenderer debug;

    public GameScreen(ViewableGameModel model) {
        Gdx.graphics.setForegroundFPS(60);
        this.model = model;
        this.player = model.getViewablePlayer();
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(this.model.getWidth(),this.model.getHeight(), gameCam);
        //Vil at gamecamera ikke skal alltid holde seg til pos (0,0)
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);
        this.world = this.model.getWorld();
        this.playerAnimation = new PlayerAnimation();
        this.batch = new SpriteBatch();
        this.player.getBody().setUserData(this.batch);

       
    }

    @Override
    public void show() {
        //FIXME må fikse slik at kamera følger playermodel
        this.debug = new Box2DDebugRenderer();
        this.model.getWorld();
        gameCam.position.set(gameCam.viewportWidth ,gameCam.viewportHeight,0f);

        gameCam.update();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //this.model.getSpriteBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        //hud.getStage().draw();
        // Tegner spilleren
        this.dt+=Gdx.graphics.getDeltaTime();
        this.batch.begin();
        this.batch.draw(this.playerAnimation.getAnimation(this.player.getPlayerState()).getKeyFrame(dt,true),
                this.player.getX(),this.player.getY(), this.player.getWidth(), this.player.getHeight());
        this.batch.end();
        this.debug.render(world,gameCam.combined);

        //object.render();
    }

    // Oppdateringer
    private void update(float delta) {
        // TODO her kalle på PLayerController.java handleINput.
        gameCam.update();

        
        // renderer.setView(gameCam); MÅ finne ut hva som skal være renderer object
    }
    

    @Override
    public void dispose() {
        this.batch.dispose();
    }
    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
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

