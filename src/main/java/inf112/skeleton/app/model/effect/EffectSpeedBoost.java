package inf112.skeleton.app.model.effect;

public class EffectSpeedBoost extends Effect {
    private static final float SPEED_BOOST = 2;
    private static final float JUMP_BOOST = 1;

    /**
     * Makes you run twice as fast.
     *
     * @param duration of effect in ticks/steps
     */
    public EffectSpeedBoost(int duration) {
        super(duration, SPEED_BOOST, JUMP_BOOST);
    }
}
