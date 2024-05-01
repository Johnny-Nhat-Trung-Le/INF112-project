package inf112.lilBro.app.view;

import inf112.lilBro.app.model.PlayerStateGettable;
import inf112.lilBro.app.model.effect.Effect;

import java.util.List;

public interface ViewablePlayerModel extends Sizeable, Positionable, PlayerStateGettable {
    /**
     * Gets the HP of the player
     *
     * @return the amount of HP left of the player
     */
    int getHp();

    /**
     * @return all current {@link Effect}s, if there is any
     */
    List<ViewableEffect> getEffects();

    /**
     * @return players item, if there is any (can be {@code null})
     */
    ViewableItem getItem();
}
