package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import inf112.skeleton.app.controller.ControllableGameModel;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;

import java.util.Iterator;


public class GameModel implements ViewableGameModel, ControllableGameModel, ContactListener {
    private int width = 600;
    private int height = 750;
    @Override
    public float getWidth() {
       return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }

    @Override
    public ControllablePlayerModel getControllablePlayer() {
        return null;
    }

    @Override
    public void setState(GameState state) {

    }

    @Override
    public GameState getState() {
        return null;
    }

    @Override
    public ViewablePlayerModel getViewablePlayer() {
        return null;
    }

    @Override
    public Iterator<ViewableTile> getForegroundTiles() {
        return null;
    }

    @Override
    public Iterator<ViewableTile> getBackgroundTiles() {
        return null;
    }

    @Override
    public Iterator<ViewableItem> getItems() {
        return null;
    }

    @Override
    public void step() {

    }
}
