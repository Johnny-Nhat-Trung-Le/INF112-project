package inf112.skeleton.app.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import inf112.skeleton.app.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TestController {
    private ControllableGameModel mockGameModel;
    private ControllableLevel mockLevel;
    private ControllablePlayerModel mockPlayerModel;
    private Controller controller;


    @BeforeEach
    public void setup() {
        mockGameModel = mock(ControllableGameModel.class);
        mockLevel = mock(ControllableLevel.class);
        mockPlayerModel = mock(ControllablePlayerModel.class);
        when(mockGameModel.getControllableLevel()).thenReturn(mockLevel);
        when(mockLevel.getControllablePlayer()).thenReturn(mockPlayerModel);
        controller = new Controller(mockGameModel);
        Application app = Mockito.mock(Application.class);
        Gdx.app = app;
    }

    @Test
    public void testKeyDownSpace() {
        controller.keyDown(Input.Keys.SPACE);
        verify(mockPlayerModel, times(1)).useItem();
    }

    @Test
    public void testKeyDownMoveUp() {
        controller.keyDown(Input.Keys.W);
        verify(mockPlayerModel, times(1)).moveUp(true);
    }

    @Test
    public void testKeyUpMoveUp() {
        controller.keyUp(Input.Keys.W);
        verify(mockPlayerModel, times(1)).moveUp(false);
    }

    @Test
    public void testKeyDownMoveDown() {
        controller.keyDown(Input.Keys.S);
        verify(mockPlayerModel, times(1)).moveDown(true);
    }

    @Test
    public void testKeyUpMoveDown() {
        controller.keyUp(Input.Keys.S);
        verify(mockPlayerModel, times(1)).moveDown(false);
    }

    @Test
    public void testKeyDownMoveLeft() {
        controller.keyDown(Input.Keys.A);
        verify(mockPlayerModel, times(1)).moveLeft(true);
    }

    @Test
    public void testKeyUpMoveLeft() {
        controller.keyUp(Input.Keys.A);
        verify(mockPlayerModel, times(1)).moveLeft(false);
    }

    @Test
    public void testKeyDownMoveRight() {
        controller.keyDown(Input.Keys.D);
        verify(mockPlayerModel, times(1)).moveRight(true);
    }

    @Test
    public void testKeyUpMoveRight() {
        controller.keyUp(Input.Keys.D);
        verify(mockPlayerModel, times(1)).moveRight(false);
    }

    @Test
    public void testKeyUpChangeStatePauseToActive() {
        when(mockGameModel.getState()).thenReturn(GameState.PAUSE);
        controller.keyUp(Input.Keys.P);
        verify(mockGameModel, times(1)).setState(GameState.ACTIVE);
    }

    @Test
    public void testKeyUpChangeStateActiveToPause() {
        when(mockGameModel.getState()).thenReturn(GameState.ACTIVE);
        controller.keyUp(Input.Keys.P);
        verify(mockGameModel, times(1)).setState(GameState.PAUSE);
    }

    @Test
    public void testKeyUpReset() {
        when(mockGameModel.getState()).thenReturn(GameState.GAME_OVER);
        controller.keyUp(Input.Keys.R);
        verify(mockGameModel, times(1)).setState(GameState.MAIN_MENU);
    }

    @Test
    public void testKeyUpHelpFromMain() {
        when(mockGameModel.getState()).thenReturn(GameState.MAIN_MENU);
        controller.keyUp(Input.Keys.H);
        verify(mockGameModel, times(1)).setState(GameState.INFO);
    }

    @Test
    public void testKeyUpHelpFromPause() {
        when(mockGameModel.getState()).thenReturn(GameState.PAUSE);
        controller.keyUp(Input.Keys.H);
        verify(mockGameModel, times(1)).setState(GameState.INFO);
        when(mockGameModel.getState()).thenReturn(GameState.INFO);

    }

    @Test
    public void testKeyUpHelpToMain() {
        when(mockGameModel.getState()).thenReturn(GameState.INFO);
        controller.keyUp(Input.Keys.B);
        verify(mockGameModel, times(1)).setState(GameState.MAIN_MENU);
    }

    @Test
    public void testKeyUpExitGame() {
        when(mockGameModel.getState()).thenReturn(GameState.ACTIVE);
        controller.keyUp(Input.Keys.ESCAPE);
        verify(Gdx.app, times(1)).exit();
    }
}
