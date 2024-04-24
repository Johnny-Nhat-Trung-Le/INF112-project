package inf112.skeleton.app.model;

import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.EventDeath;

public class Health implements IHealth {
    private final Object owner;
    private final EventBus bus;
    private final int max;
    private int health;

    /**
     * Creates a health for the owner. If the health is reduced to
     * zero a {@link EventDeath} will be posted in the {@link EventBus}.
     *
     * @param owner of the health
     * @param bus to post {@link EventDeath} on {@code health == 0}
     * @param max allowed health
     */
    public Health(Object owner, EventBus bus, int max) {
        this(owner, bus, max, max);
    }

    /**
     * Creates a health for the owner. If the health is reduced to
     * zero a {@link EventDeath} will be posted in the {@link EventBus}.
     *
     * @param owner of the health
     * @param bus to post {@link EventDeath} when health is reduced to zero
     * @param health at the start
     * @param max allowed health
     */
    public Health(Object owner, EventBus bus, int health, int max) {
        this.owner = owner;
        this.bus = bus;
        this.max = max;
        this.health = health;
    }

    @Override
    public void regenerate(int amount) {
        health = Math.min(health + amount, max);
    }

    @Override
    public void damage(int amount) {
        health = Math.max(health - amount, 0);
        if (health == 0) {
            bus.post(new EventDeath(owner));
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getMaxHealth() {
        return max;
    }
}
