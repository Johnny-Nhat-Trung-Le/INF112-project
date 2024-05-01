package inf112.lilBro.app.view;

import inf112.lilBro.app.model.GameStateGettable;


public interface ViewableGameModel extends GameStateGettable {
    /**
     * @return the current active {@link ViewableLevel}, can be {@code null}
     */
    ViewableLevel getViewableLevel();
}
