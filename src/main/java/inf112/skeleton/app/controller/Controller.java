package inf112.skeleton.app.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class Controller extends InputAdapter {
    private ControllableGameModel model;
    private ControllablePlayerModel playerModel;

    public Controller(ControllableGameModel model){
        this.model = model;
        this.playerModel = this.model.getControllablePlayer();
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO - legg på mer cases for ulike taster. (eks. useItem, pause, exit etc)
        //TODO problem SWITCH er messed up den registrerer alle keys
//        switch(keycode){
//            case Keys.W: this.playerModel.moveUp(true);
//            case Keys.A: this.playerModel.moveLeft(true);
//            case Keys.S: this.playerModel.moveDown(true);
//            case Keys.D: this.playerModel.moveRight(true);
//
//        }
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
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
//        if(Keys.W == keycode){
//            this.playerModel.moveUp(false);
//        }
//        else if(Keys.A == keycode){
//            this.playerModel.moveLeft(false);
//        }
//        else if(Keys.S == keycode){
//            this.playerModel.moveDown(false);
//        }
//        else if(Keys.D == keycode){
//            this.playerModel.moveRight(false);
//        }
//        else {
//            this.playerModel.moveIdle(false);
//        }
        this.playerModel.moveIdle(false);
        return false;
    }

}
