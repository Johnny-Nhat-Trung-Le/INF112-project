package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewablePlayerModel;

public class GameScreen implements Screen {
    private ViewableGameModel model;
    private ViewablePlayerModel player;
    private OrthographicCamera gameCam;
    //ViewPort viser hvordan grafikken skal se ut mtp skjermen til enheten
    private Viewport gamePort;
    //private Hud hud;

    public GameScreen(ViewableGameModel model) {
        Gdx.graphics.setForegroundFPS(60);
        this.model = model;
        this.player = model.getPlayer();
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(this.model.getWidth(),this.model.getHeight(), gameCam);
        //Vil at gamecamera ikke skal alltid holde seg til pos (0,0)
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);

       // hud = new Hud(model.getSpriteBatch(), model.getWidth(), model.getHeight());
       
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //this.model.getSpriteBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
        //hud.getStage().draw();

       /*  this.model.getSpriteBatch().begin();
		this.model.getSpriteBatch().draw(spriteImage, player.getPosX(), player.getPosY(), spriteRect.width, spriteRect.height);
		this.model.getSpriteBatch().end(); */

    
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

