package inf112.skeleton.app.view;

import inf112.skeleton.app.model.PlayerStateGettable;

public interface ViewablePlayerModel extends Sizeable, Positionable, PlayerStateGettable {
    /**
     * Get the Hp of the player
     * @return the amount of Hp left of the player
     */
    Integer getHp();

    /**
     * @return the current effect, if there is any
     */
    ViewableEffect getEffect();

    /**
     * @return players item, if there is any
     */
    ViewableItem getItem();
}
