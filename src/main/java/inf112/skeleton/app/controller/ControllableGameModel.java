package inf112.skeleton.app.controller;

import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.grid.IGrid;
import inf112.skeleton.app.model.*;

import java.util.Iterator;

public interface ControllableGameModel extends GameStateGettable, Stepable {
    /**
     * @return {@link ControllablePlayerModel} of the model
     */
    ControllablePlayerModel getControllablePlayer();

    /**
     * Sets the current {@link GameState}
     * @param state the new {@link GameState}
     */
    void setState(GameState state);
}
