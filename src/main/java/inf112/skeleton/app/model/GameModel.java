package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.controller.ControllableGameModel;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.controller.Controller;
import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameModel implements ViewableGameModel, ControllableGameModel, ContactListener {
    private static final float GRAVITY = 10;
    private int width = 600;
    private int height = 750;
    private final List<TileModel> foreground;
    private final World world;

    private PlayerModel playerModel;
    public GameModel() {
        // TODO - fix initSize after game size
        foreground = new ArrayList<>();
        world = new World(new Vector2(0, GRAVITY), true);
        this.playerModel = new PlayerModel(this.world);

    }
    @Override
    public World getWorld(){return this.world;}
    @Override
    public float getWidth() {
       return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public void beginContact(Contact contact) {}

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold manifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {}

    @Override
    public ControllablePlayerModel getControllablePlayer() {
        return this.playerModel;
    }

    @Override
    public void setState(GameState state) {}

    @Override
    public GameState getState() {
        return null;
    }

    @Override
    public ViewablePlayerModel getViewablePlayer() {
        return this.playerModel;
    }

    @Override
    public Iterator<ViewableTile> getForegroundTiles() {
        return foreground.stream().map((tile) -> (ViewableTile) tile).iterator();
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
    public void step() {}
}
