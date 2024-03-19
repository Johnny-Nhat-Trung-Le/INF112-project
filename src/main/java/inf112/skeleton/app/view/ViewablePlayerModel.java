package inf112.skeleton.app.view;

import inf112.skeleton.app.model.Physicable;
import inf112.skeleton.app.model.PlayerStateGettable;

public interface ViewablePlayerModel extends Sizeable, Positionable, PlayerStateGettable {
    /**
     * Get the Hp of the player
     * @return the amount of Hp left of the player
     */
    Integer getHp();
}
