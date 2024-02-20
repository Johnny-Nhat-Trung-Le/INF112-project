package inf112.skeleton.app.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class Controller implements InputProcessor{
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
    /**
     * Resten er vel irrelevant??
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
