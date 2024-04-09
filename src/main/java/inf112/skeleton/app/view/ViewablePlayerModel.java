package inf112.skeleton.app.view;

import inf112.skeleton.app.model.PlayerStateGettable;
import inf112.skeleton.app.model.effect.Effect;

import java.util.List;

public interface ViewablePlayerModel extends Sizeable, Positionable, PlayerStateGettable {
    /**
     * Get the Hp of the player
     * @return the amount of Hp left of the player
     */
    Integer getHp();

    /**
     * @return the current {@link Effect}s, if there is any
     */
    List<ViewableEffect> getEffects();

    /**
     * @return players item, if there is any
     */
    ViewableItem getItem();
}
