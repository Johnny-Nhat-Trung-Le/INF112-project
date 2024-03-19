package inf112.skeleton.app.view;

import inf112.skeleton.app.model.Durability;
import inf112.skeleton.app.model.Effect;
import inf112.skeleton.app.view.ViewableItem;

public interface ViewableEffect {
    /**
     * @return the duration of the {@link Effect}
     */
    Durability getDuration();

    /**
     * @return the {@link ViewableItem} that creates the {@link Effect}
     */
    ViewableItem getItem();
}
