package inf112.skeleton.app.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.event.EventStep;
import inf112.skeleton.app.model.GameState;


public class Controller extends InputAdapter implements EventHandler {
    private final ControllableGameModel model;

    public Controller(ControllableGameModel model) {
        this.model = model;
    }

    @Override
    public boolean keyDown(int keycode) {
        ControllablePlayerModel player = model.getControllablePlayer();
        // TODO - legg på mer cases for ulike taster. (eks. useItem, pause, exit etc)
            switch (keycode) {
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
        public boolean keyUp ( int keycode){
            ControllablePlayerModel player = model.getControllablePlayer();

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
                    GameState currentGameState = model.getState();
                    if (currentGameState.equals(GameState.ACTIVE)) {
                        model.setState(GameState.MAIN_MENU);
                    } else if (currentGameState.equals(GameState.MAIN_MENU)) {
                        model.setState(GameState.ACTIVE);
                    }
                    break;
            }
            return true;
        }

        @Override
        public void handleEvent (Event event){
            if (event instanceof EventStep e) {
                model.step(e.timeStep());
            }
        }

}
