package inf112.skeleton.app.view;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.view.screen.GameScreen;


public class GameView extends Game {
    private final GameModel model;
    private final EventBus bus;
    private final InputProcessor processor;

    public GameView(GameModel model, EventBus bus, InputProcessor processor){
        this.model = model;
        this.bus = bus;
        this.processor = processor;
    }

    @Override
    public void create() {
        setScreen(new GameScreen(model, bus, processor));
    }

    public void render(){
        super.render();
    }

}
