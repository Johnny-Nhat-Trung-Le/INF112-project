package inf112.skeleton.app.model;

/**
 * @param duration number of steps the effect is applied
 * @param speedBoost speed multiplier
 * @param jumpBoost jump boost multiplier
 */
public record Effect(int duration, float speedBoost, float jumpBoost) {}
