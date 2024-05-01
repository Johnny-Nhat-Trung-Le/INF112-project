package inf112.lilBro.app.view;

import inf112.lilBro.app.model.Durability;

public interface ViewableItem extends Positionable, Sizeable {
    /**
     * @return a description of the item
     */
    String getDescription();

    /**
     * @return the name of the item
     */
    String getName();

    /**
     * @return the current durability of the item
     */
    Durability getDurability();
}
