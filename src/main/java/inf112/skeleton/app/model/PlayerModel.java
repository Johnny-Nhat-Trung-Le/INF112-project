package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.view.ViewablePlayerModel;

public class PlayerModel implements ControllablePlayerModel, ViewablePlayerModel, Physicable {

    public PlayerModel(World world){}

    @Override
    public void moveUp(boolean value) {

    }

    @Override
    public void moveDown(boolean value) {

    }

    @Override
    public void moveLeft(boolean value) {

    }

    @Override
    public void moveRight(boolean value) {

    }

    @Override
    public void useItem() {

    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public void step() {

    }
}
