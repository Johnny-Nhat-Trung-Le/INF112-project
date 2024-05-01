package inf112.skeleton.app.view;

import inf112.skeleton.app.model.Durability;
import inf112.skeleton.app.model.effect.Effect;

public interface ViewableEffect {
    /**
     * @return the remaining duration of the {@link Effect}
     */
    Durability getDuration();
}
