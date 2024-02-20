package inf112.skeleton.app.controller;

import inf112.skeleton.app.model.GameStateGettable;

public interface ControllableGameModel extends GameStateGettable {
    /**
     * @return {@link ControllablePlayerModel} of the model
     */
    ControllablePlayerModel getPlayer();

}
