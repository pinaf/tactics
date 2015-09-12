package tactics.engine.game;

import java.util.concurrent.TimeUnit;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link GmeClocked}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class GmeClockedTest {

    /**
     * Honors target rate.
     */
    @Test
    public void honorsTargetRate() {
        final double rate = 60.0;
        final long limit = 120L;
        final Game game = new GmeClocked(rate, new GmeLoop());
        final long start = System.nanoTime();
        game.start(new ExitCount(limit, game));
        final long delta = System.nanoTime() - start;
        final long expected = TimeUnit.SECONDS.toNanos((long) (limit / rate));
        MatcherAssert.assertThat(game.cycles(), Matchers.is(limit));
        MatcherAssert.assertThat(
            (double) (delta - expected),
            Matchers.closeTo(0.0D, (double) expected * 0.1D)
        );
    }

}
