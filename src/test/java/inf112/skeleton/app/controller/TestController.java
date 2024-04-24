package inf112.skeleton.app.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.model.GameState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito .*;

public class TestController {
    private ControllableGameModel mockGameModel;
    private ControllablePlayerModel mockPlayerModel;
    private Controller controller;

    @BeforeAll
    public static void beforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationListener() {
            @Override
            public void create() {

            }

            @Override
            public void resize(int width, int height) {

            }

            @Override
            public void render() {

            }

            @Override
            public void pause() {

            }

            @Override
            public void resume() {

            }

            @Override
            public void dispose() {

            }
        };
        new HeadlessApplication(listener, config);
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    @BeforeEach
    public void setup() {
        mockGameModel = mock(ControllableGameModel.class);
        mockPlayerModel = mock(ControllablePlayerModel.class);
        when(mockGameModel.getControllablePlayer()).thenReturn(mockPlayerModel);
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

    // can add more keyup and keydown

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
    public void testKeyUpExitGame() {
        when(mockGameModel.getState()).thenReturn(GameState.ACTIVE);
        controller.keyUp(Input.Keys.ESCAPE);
        verify(Gdx.app, times(1)).exit();
    }
}
