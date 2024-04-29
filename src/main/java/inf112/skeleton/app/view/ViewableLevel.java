package inf112.skeleton.app.view;

import java.util.List;

public interface ViewableLevel {
    /**
     * @return the active {@link ViewablePlayerModel} in the game
     */
    ViewablePlayerModel getViewablePlayer();

    /**
     * @return tiles that are can be interacted with in the game
     */
    List<ViewableTile> getForegroundTiles();

    /**
     * @return tiles that are only visual in the game
     */
    List<ViewableTile> getBackgroundTiles();

    /**
     * @return all the active items in the game
     */
    List<ViewableItem> getItems();
}
