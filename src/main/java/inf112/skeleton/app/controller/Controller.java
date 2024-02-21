package inf112.skeleton.app.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class Controller extends InputAdapter {
    private ControllableGameModel model;
    private ControllablePlayerModel playerModel;
    public Controller(ControllableGameModel model){
        this.model = model;
        this.playerModel = this.model.getPlayer();

    }

    @Override
    public boolean keyDown(int keycode) {
       
        // TODO legg på mer cases for ulike taster. (eks. useItem, pause, exit etc)
        if(Keys.W == keycode){
            this.playerModel.moveUp();
        }
        else if(Keys.A == keycode){
            this.playerModel.moveLeft();
        }
        else if(Keys.S == keycode){
            this.playerModel.moveDown();
        }
        else if(Keys.D == keycode){
            this.playerModel.moveRight();
        }
        /* 
        switch(keycode){
            case Keys.W: this.playerModel.moveUp();
            case Keys.A: this.playerModel.moveLeft();
            case Keys.S: this.playerModel.moveDown();
            case Keys.D: this.playerModel.moveRight(); 
        }
        */
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(Keys.W == keycode){
            this.playerModel.moveUp();
        }
        else if(Keys.A == keycode){
            this.playerModel.moveLeft();
        }
        else if(Keys.S == keycode){
            this.playerModel.moveDown();
        }
        else if(Keys.D == keycode){
            this.playerModel.moveRight();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
      return false;
    }
}
