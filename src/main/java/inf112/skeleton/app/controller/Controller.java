package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.event.EventStep;


public class Controller extends InputAdapter implements EventHandler {
    private final ControllableGameModel model;
    private boolean wasMenu = true;

    public Controller(ControllableGameModel model) {
        this.model = model;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (model.getControllableLevel() == null) return false;
        ControllablePlayerModel player = model.getControllableLevel().getControllablePlayer();

        switch (keycode) {
            case Keys.SPACE:
                player.useItem();
                break;
            case Keys.W:
                player.moveUp(true);
                break;
            case Keys.A:
                player.moveLeft(true);
                break;
            case Keys.S:
                player.moveDown(true);
                break;
            case Keys.D:
                player.moveRight(true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (model.getControllableLevel() == null) return false;
        ControllablePlayerModel player = model.getControllableLevel().getControllablePlayer();
        switch (keycode) {
            case Keys.W:
                player.moveUp(false);
                break;
            case Keys.A:
                player.moveLeft(false);
                break;
            case Keys.S:
                player.moveDown(false);
                break;
            case Keys.D:
                player.moveRight(false);
                break;
            case Keys.P:
                switch (model.getState()) {
                    case MAIN_MENU, PAUSE:
                        model.setState(GameState.ACTIVE);
                        break;
                    case ACTIVE:
                        model.setState(GameState.PAUSE);
                        break;
                }
                break;
            case Keys.R:
                if (model.getState().equals(GameState.GAME_OVER)) {
                    model.setState(GameState.MAIN_MENU);
                }
                break;
            case Keys.H:
                switch (model.getState()) {
                    case MAIN_MENU:
                        model.setState(GameState.INFO);
                        wasMenu = true;
                        break;
                    case PAUSE:
                        model.setState(GameState.INFO);
                        wasMenu = false;
                        break;
                }
                break;
            case Keys.B:
                if (model.getState() == GameState.INFO) {
                    if (wasMenu) {
                        model.setState(GameState.MAIN_MENU);
                    } else {
                        model.setState(GameState.ACTIVE);
                    }
                }
                break;
            case Keys.ESCAPE:
                Gdx.app.exit();
        }
        return true;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventStep e) {
            if (model.getControllableLevel() == null) return;
            model.getControllableLevel().step(e.timeStep());
        }
    }

}
