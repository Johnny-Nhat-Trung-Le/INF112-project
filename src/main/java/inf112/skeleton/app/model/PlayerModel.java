package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.view.ViewablePlayerModel;

public class PlayerModel implements ControllablePlayerModel, ViewablePlayerModel, Physicable {
    private float x=0;
    private float y = 0;
    private float width = 40;

    private float height = 40;

    private World world;
    public PlayerModel(World world){
        this.world = world;
    }

    @Override
    public void moveUp(boolean value) {
        System.out.println("PlayerModel.moveUp");
    }

    @Override
    public void moveDown(boolean value) {
        System.out.println("PlayerModel.moveDown");
    }

    @Override
    public void moveLeft(boolean value) {
        System.out.println("PlayerModel.moveLeft");

    }

    @Override
    public void moveRight(boolean value) {
        System.out.println("PlayerModel.moveRight");
    }

    @Override
    public void useItem() {

    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public float getWidth() {
        return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public void step() {

    }
}
