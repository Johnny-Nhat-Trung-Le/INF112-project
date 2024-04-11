package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.event.EventResetGame;
import inf112.skeleton.app.model.event.EventStep;
import inf112.skeleton.app.model.GameState;


public class Controller extends InputAdapter implements EventHandler {
    private  ControllableGameModel model;

    public Controller(ControllableGameModel model) {
        this.model = model;
    }

    @Override
    public boolean keyDown(int keycode) {
        ControllablePlayerModel player = model.getControllablePlayer();
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
                    if(model.getState().equals(GameState.GAME_OVER)){
                        model.setState(GameState.MAIN_MENU);
                    }
                    break;
                case Keys.ESCAPE:
                    Gdx.app.exit();
            }
            return true;
        }

        @Override
        public void handleEvent (Event event){
            if (event instanceof EventStep e) {
                model.step(e.timeStep());
            }
            if (event instanceof EventResetGame e){
                model = e.gameModel();
            }
        }

}
