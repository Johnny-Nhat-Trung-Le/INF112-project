package inf112.skeleton.app.model;

import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.EventDeath;

public interface IHealth {
    /**
     * Regenerates the amount. Health caps at the max
     * specified in the constructor.
     *
     * @param amount regenerated
     */
    void regenerate(int amount);

    /**
     * Reduces health by the specified amount. Health
     * stops at zero. If the health is zero it will post
     * {@link EventDeath} in the Health's {@link EventBus}
     *
     * @param amount damaged
     */
    void damage(int amount);

    /**
     * @return current health
     */
    int getHealth();

    /**
     * @return the maximum allowed health
     */
    int getMaxHealth();
}
