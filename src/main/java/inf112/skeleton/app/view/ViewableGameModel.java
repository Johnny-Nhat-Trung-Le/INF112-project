package inf112.skeleton.app.view;

import inf112.skeleton.app.grid.IGrid;
import inf112.skeleton.app.model.GameStateGettable;

import java.util.Iterator;
import java.util.List;

public interface ViewableGameModel extends Sizeable, GameStateGettable {
    /**
     * @return the active {@link ViewablePlayerModel} in the game
     */
    ViewablePlayerModel getViewablePlayer();

    /**
     * @return tiles that are can be interacted with in the game
     */
    Iterator<ViewableTile> getForegroundTiles();

    /**
     * @return tiles that are only visual in the game
     */
    Iterator<ViewableTile> getBackgroundTiles();

    /**
     * @return all the active items in the game
     */
    Iterator<ViewableItem> getItems();
}
