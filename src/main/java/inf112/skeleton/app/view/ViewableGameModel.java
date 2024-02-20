package inf112.skeleton.app.view;

import inf112.skeleton.app.grid.IGrid;
import inf112.skeleton.app.model.GameStateGettable;

import java.util.List;

public interface ViewableGameModel extends Sizeable, GameStateGettable {
    /**
     * @return the active {@link ViewablePlayerModel} in the game
     */
    ViewablePlayerModel getPlayer();

    /**
     * @return tiles that are can be interacted with in the game
     */
    IGrid<ViewableTile> getForegroundTiles();

    /**
     * @return tiles that are only visual in the game
     */
    IGrid<ViewableTile> getBackgroundTiles();

    /**
     * @return all the items in the game
     */
    List<ViewableItem> getItems();
}
