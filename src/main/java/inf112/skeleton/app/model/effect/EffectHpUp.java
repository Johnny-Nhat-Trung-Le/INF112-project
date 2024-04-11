package inf112.skeleton.app.model.effect;

public class EffectHpUp extends Effect {
    private static final float SPEED_BOOST = 1;
    private static final float JUMP_BOOST = 1;

    /**
     * Restores one hp, giving no movement-effects.
     *
     * @param duration number of steps the effect is applied
     */
    public EffectHpUp(int duration) {
        super(duration, SPEED_BOOST, JUMP_BOOST);
    }
}
