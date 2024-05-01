package inf112.lilBro.app.view;

import java.util.List;

public interface ViewableLevel {
    /**
     * @return the current {@link ViewablePlayerModel} from the game
     */
    ViewablePlayerModel getViewablePlayer();

    /**
     * @return all tiles that are can be interacted with in the game
     */
    List<ViewableTile> getForegroundTiles();

    /**
     * @return all tiles that are only visual in the game
     */
    List<ViewableTile> getBackgroundTiles();

    /**
     * @return all the items currently in the {@link com.badlogic.gdx.physics.box2d.World}
     */
    List<ViewableItem> getItems();
}
