package inf112.lilBro.app.view;

import inf112.lilBro.app.model.Durability;
import inf112.lilBro.app.model.effect.Effect;

public interface ViewableEffect {
    /**
     * @return the remaining duration of the {@link Effect}
     */
    Durability getDuration();
}
