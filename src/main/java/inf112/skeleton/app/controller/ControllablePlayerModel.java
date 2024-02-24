package inf112.skeleton.app.controller;

import inf112.skeleton.app.model.Stepable;

public interface ControllablePlayerModel extends Stepable {
    /**
     * Sets the player's upwards facing directional movement-variable
     * to the specified value.
     * @param value boolean deciding if the player is trying to move upwards.
     */
    void moveUp(boolean value);

    /**
     * Sets the player's downwards facing directional movement-variable
     * to the specified value.
     * @param value boolean deciding if the player is trying to move downwards.
     */
    void moveDown(boolean value);

    /**
     * Sets the player's leftwards facing directional movement-variable
     * to the specified value.
     * @param value boolean deciding if the player is trying to move leftwards.
     */
    void moveLeft(boolean value);

    /**
     * Sets the player's rightwards facing directional movement-variable
     * to the specified value.
     * @param value boolean deciding if the player is trying to move rightwards.
     */
    void moveRight(boolean value);

    /**
     * Sets the player in the idle state
     */
    void moveIdle(boolean value);

    /**
     * Uses the player's item. If the player does
     * not have an item nothing happens.
     */
    void useItem();
}
