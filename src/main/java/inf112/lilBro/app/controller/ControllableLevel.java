package inf112.lilBro.app.controller;

import inf112.lilBro.app.model.Stepable;

public interface ControllableLevel extends Stepable {
    /**
     * @return {@link ControllablePlayerModel} of the model
     */
    ControllablePlayerModel getControllablePlayer();

    /**
     * Resets the level by overriding all values.
     */
    void reset();

    /**
     * Activates the level in context of {@link inf112.lilBro.app.event.EventBus}.
     */
    void activate();

    /**
     * Disables the level in context of {@link inf112.lilBro.app.event.EventBus}.
     */
    void disable();
}
