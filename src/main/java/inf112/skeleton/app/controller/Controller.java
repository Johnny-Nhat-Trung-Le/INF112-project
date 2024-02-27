package inf112.skeleton.app.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.event.EventStep;

public class Controller extends InputAdapter implements EventHandler {
    private ControllableGameModel model;
    private ControllablePlayerModel playerModel;

    public Controller(ControllableGameModel model){
        this.model = model;
        this.playerModel = this.model.getControllablePlayer();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Keys.W: this.playerModel.moveUp(true);
            case Keys.A: this.playerModel.moveLeft(true);
            case Keys.S: this.playerModel.moveDown(true);
            case Keys.D: this.playerModel.moveRight(true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(Keys.W == keycode){
            this.playerModel.moveUp(false);
        }
        else if(Keys.A == keycode){
            this.playerModel.moveLeft(false);
        }
        else if(Keys.S == keycode){
            this.playerModel.moveDown(false);
        }
        else if(Keys.D == keycode){
            this.playerModel.moveRight(false);
        }
        return false;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventStep e) {
            model.step(e.timeStep());
        }
    }
}
