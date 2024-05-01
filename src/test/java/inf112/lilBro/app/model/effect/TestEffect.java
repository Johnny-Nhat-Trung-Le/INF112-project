package inf112.lilBro.app.model.effect;

import inf112.lilBro.app.model.Durability;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEffect {
    private static final int ITERATIONS = 60;
    private static final int DURATION = 30;
    private static Supplier<Effect> effectImpl;
    private Effect effect;

    @BeforeAll
    public static void beforeAll() {
        effectImpl = () -> new EffectJumpBoost(DURATION);
    }

    @BeforeEach
    public void beforeEach() {
        effect = effectImpl.get();
    }

    @Test
    public void testDuration() {
        Durability duration = effect.getDuration();
        int max = duration.maximum();
        int last = duration.remaining();

        assertEquals(max, last, "The effect did not start with full duration!");

        for (int i = 0; i < ITERATIONS; i++) {
            effect.step();

            duration = effect.getDuration();
            int current = duration.remaining();
            int currentMax = duration.maximum();

            assertEquals(max, currentMax, "The maximum duration of the effect did change!");
            assertEquals(Math.max(last - 1, 0), current, "The duration was not reduced by one!");

            last = current;
        }

        if (DURATION <= ITERATIONS) {
            assertEquals(0, last, "The effects duration did not end on zero");
            assertTrue(effect.hasEnded(), "Effect::hasEnded did not return 'true' when 'duration==0'");
        }
    }
}
