package inf112.skeleton.app.model.effect;

public class EffectJumpBoost extends Effect {
    private static final float SPEED_BOOST = 1;
    private static final float JUMP_BOOST = 3 / 2f;

    /**
     * Makes you higher.
     *
     * @param duration of effect in ticks/steps
     */
    public EffectJumpBoost(int duration) {
        super(duration, SPEED_BOOST, JUMP_BOOST);
    }
}
