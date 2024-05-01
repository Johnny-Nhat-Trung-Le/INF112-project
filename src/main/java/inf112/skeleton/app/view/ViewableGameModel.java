package inf112.skeleton.app.view;

import inf112.skeleton.app.model.GameStateGettable;


public interface ViewableGameModel extends GameStateGettable {
    /**
     * @return the current active {@link ViewableLevel}, can be {@code null}
     */
    ViewableLevel getViewableLevel();
}
