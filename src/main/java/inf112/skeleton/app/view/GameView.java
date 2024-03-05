package inf112.skeleton.app.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.view.screen.GameOverScreen;
import inf112.skeleton.app.view.screen.GameScreen;

import inf112.skeleton.app.view.screen.MenuScreen;
import inf112.skeleton.app.view.screen.PauseScreen;


public class GameView extends Game {
    private GameModel model;
    private final EventBus bus;
    private final InputProcessor processor;
    private GameState gameState;
    public GameView(GameModel model, EventBus bus, InputProcessor processor){
        this.model = model;
        this.bus = bus;
        this.processor = processor;
        this.gameState =  GameState.MAIN_MENU;
    }

    @Override
    public void create() {
        setScreen(new MenuScreen(processor));

    }
    public void render(){
        if(model.getState()!= gameState){
            gameState = model.getState();
            switch(gameState){
                case ACTIVE -> setScreen(new GameScreen(model,bus, processor));
                case MAIN_MENU -> setScreen(new MenuScreen(processor));
                case PAUSE -> setScreen(new PauseScreen(processor));
                case GAME_OVER -> setScreen(new GameOverScreen(processor));
            }
        }
        super.render();
    }
}
