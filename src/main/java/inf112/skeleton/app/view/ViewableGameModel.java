package inf112.skeleton.app.view;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.model.GameStateGettable;
import inf112.skeleton.app.model.PlayerModel;

import java.util.List;


public interface ViewableGameModel extends GameStateGettable {
    /**
     * @return the active {@link ViewablePlayerModel} in the game
     */
    ViewablePlayerModel getViewablePlayer();

    /**
     * @return tiles that are can be interacted with in the game
     */
    Iterable<ViewableTile> getForegroundTiles();

    /**
     * @return tiles that are only visual in the game
     */
    Iterable<ViewableTile> getBackgroundTiles();

    /**
     * @return all the active items in the game
     */
    Iterable<ViewableItem> getItems();

}
