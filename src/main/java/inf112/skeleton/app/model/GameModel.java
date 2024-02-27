package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.controller.ControllableGameModel;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements ViewableGameModel, ControllableGameModel, ContactListener {
    private static final float GRAVITY = -9.81f;
    private static final float WIND = 0;
    private static final int VELOCITY_ITERATIONS = 1;
    private static final int POSITION_ITERATIONS = 1;
    private static final float WIDTH = 20;
    private static final float HEIGHT = 20;

    private final List<TileModel> foreground;
    private final World world;
    private final PlayerModel player;

    public GameModel() {
        foreground = new ArrayList<>();
        world = new World(new Vector2(WIND, GRAVITY), true);
        player = new PlayerModel(world);
    }

    @Override
    public float getWidth() {
       return WIDTH;
    }

    @Override
    public float getHeight() {
        return HEIGHT;
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
        return null;
    }

    @Override
    public void setState(GameState state) {}

    @Override
    public GameState getState() {
        return null;
    }

    @Override
    public ViewablePlayerModel getViewablePlayer() {
        return null;
    }

    @Override
    public Iterable<ViewableTile> getForegroundTiles() {
        return foreground.stream().map((t) -> (ViewableTile) t).toList();
    }

    @Override
    public Iterable<ViewableTile> getBackgroundTiles() {
        return null;
    }

    @Override
    public Iterable<ViewableItem> getItems() {
        return null;
    }

    @Override
    public void step(float timeStep) {
        world.step(timeStep, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        player.step(timeStep);
    }
}
