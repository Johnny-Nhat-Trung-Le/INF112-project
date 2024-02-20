package inf112.skeleton.app.controller;


/**
 * Bruk dette interfacet til å sende controll info til playerModel
 */
public interface ControllablePlayerModel {
    
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
    
    void useItem();
}
