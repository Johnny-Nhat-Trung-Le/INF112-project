package inf112.skeleton.app.model;

import inf112.skeleton.app.model.event.EventItemUsedUp;
import inf112.skeleton.app.view.ViewableItem;

public interface Item extends ViewableItem {
    /**
     * Uses the item and decreases its durability. If
     * the item is used up an {@link EventItemUsedUp} will
     * be posted.
     * @return effect applied by the use of the item
     */
    public Effect use();
}
