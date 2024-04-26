package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.controller.Controller;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.event.EventGameState;
import inf112.skeleton.app.view.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TestScreen {
    private EventBus bus;
    private GameModel model;
    private Controller controller;
    private GameView view;

    @BeforeEach
    public void beforeEach(){
        bus = new EventBus();

        model = new GameModel(bus);

        controller = new Controller(model);
        bus.addEventHandler(controller);

        view = new GameView(model, bus, controller);
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        new HeadlessApplication(view);

    }

    @Test
    public void testStartState(){
        GameState startGameState = model.getState();
        assertEquals(GameState.MAIN_MENU,startGameState);
    }
    @Test
    public void testToActiveState(){
        GameState startGameState = model.getState();
        assertEquals(GameState.MAIN_MENU,startGameState);
        controller.keyUp(Input.Keys.P);
        assertEquals(GameState.ACTIVE, model.getState());
    }
    @Test
    public void testToPauseState(){
        GameState startGameState = model.getState();
        assertEquals(GameState.MAIN_MENU,startGameState);
        //Press P once, the state should be active
        controller.keyUp(Input.Keys.P);
        assertEquals(GameState.ACTIVE, model.getState());
        //Press P in the active state should switch to pause state
        controller.keyUp(Input.Keys.P);
        assertEquals(GameState.PAUSE,model.getState());
        //Press P in pause state should switch to active state
        controller.keyUp(Input.Keys.P);
        assertEquals(GameState.ACTIVE, model.getState());
    }
    @Test
    public void testGameOverState(){
        controller.keyUp(Input.Keys.P);
        bus.post(new EventGameState(GameState.GAME_OVER));
        assertEquals(GameState.GAME_OVER, model.getState());

    }
    @Test
    public void testRestartGame(){
        controller.keyUp(Input.Keys.P);
        bus.post(new EventGameState(GameState.GAME_OVER));
        assertEquals(GameState.GAME_OVER, model.getState());
        controller.keyUp(Input.Keys.R);
        assertNotEquals(GameState.GAME_OVER, model.getState());
        assertEquals(GameState.MAIN_MENU,model.getState());

    }
    @Test
    public void testVictoryState(){
        controller.keyUp(Input.Keys.P);
        bus.post(new EventGameState(GameState.VICTORY));
        assertEquals(GameState.VICTORY, model.getState());
    }
    //TODO lag test for InfoScreen

}
