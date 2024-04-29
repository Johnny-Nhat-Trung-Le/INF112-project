package inf112.skeleton.app.controller;

import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.GameStateGettable;

public interface ControllableGameModel extends GameStateGettable {
    /**
     * @return {@link ControllableLevel} of the model, can be {@code null}.
     */
    ControllableLevel getControllableLevel();

    /**
     * Sets the current {@link GameState}.
     *
     * @param state the new {@link GameState}
     */
    void setState(GameState state);

    /**
     * Sets the active {@link inf112.skeleton.app.model.ILevel}.
     *
     * @param key of level
     */
    void setLevel(String key);
}
