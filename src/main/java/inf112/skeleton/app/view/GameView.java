package inf112.skeleton.app.view;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.controller.ControllableGameModel;
import inf112.skeleton.app.controller.Controller;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.view.screen.GameScreen;
public class GameView extends Game {
    private ViewableGameModel viewModel;
    private ControllableGameModel controlModel;

    public GameView(GameModel model){
        this.viewModel = model;
        this.controlModel = model;
    }

    @Override
    public void create() {
        setScreen(new GameScreen(viewModel));

        Gdx.input.setInputProcessor(new Controller(this.controlModel));
    }

    public void render(){
        super.render();
    }

}
