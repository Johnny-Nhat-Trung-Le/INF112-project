package inf112.skeleton.app.model;

import inf112.skeleton.app.grid.IGrid;
import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.ViewablePlayerModel;
import inf112.skeleton.app.view.ViewableTile;

import java.util.List;

public class GameModel implements ViewableGameModel {
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
    public GameState getState() {
        return null;
    }

    @Override
    public ViewablePlayerModel getPlayer() {
        return null;
    }

    @Override
    public IGrid<ViewableTile> getForegroundTiles() {
        return null;
    }

    @Override
    public IGrid<ViewableTile> getBackgroundTiles() {
        return null;
    }

    @Override
    public List<ViewableItem> getItems() {
        return null;
    }
}
