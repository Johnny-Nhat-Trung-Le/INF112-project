package inf112.skeleton.app.model;

import inf112.skeleton.app.view.ViewableEffect;
import inf112.skeleton.app.view.ViewableItem;

public class Effect implements ViewableEffect {
    private final ViewableItem item;
    private final float speedBoost;
    private final float jumpBoost;
    private final int maxDuration;
    private int duration;

    /**
     * @param duration number of steps the effect is applied
     * @param speedBoost speed-boost multiplier
     * @param jumpBoost jump-boost multiplier
     */
    public Effect(ViewableItem item, int duration, float speedBoost, float jumpBoost) {
        this.item = item;
        this.speedBoost = speedBoost;
        this.jumpBoost = jumpBoost;
        this.maxDuration = duration;
        this.duration = duration;
    }

    /**
     * Decrements the duration of the {@link Effect}
     */
    public void step() {
        duration -= 1;
    }

    /**
     * @return true if the duration is non-positive
     */
    public boolean hasEnded() {
        return duration <= 0;
    }

    /**
     * @return the {@link Effect}'s speed-boost multiplier
     */
    public float getSpeedBoost() {
        return speedBoost;
    }

    /**
     * @return the {@link Effect}'s jump-boost multiplier
     */
    public float getJumpBoost() {
        return jumpBoost;
    }

    @Override
    public Durability getDuration() {
        return new Durability(duration, maxDuration);
    }

    @Override
    public ViewableItem getItem() {
        return item;
    }
}
