package inf112.lilBro.app.model.effect;

public class EffectSpeedBoost extends Effect {
    private static final float SPEED_BOOST = 3 / 2f;
    private static final float JUMP_BOOST = 1;

    /**
     * Makes you run faster.
     *
     * @param duration of effect in ticks/steps
     */
    public EffectSpeedBoost(int duration) {
        super(duration, SPEED_BOOST, JUMP_BOOST);
    }
}
