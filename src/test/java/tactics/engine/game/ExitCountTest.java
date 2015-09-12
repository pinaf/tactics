package tactics.engine.game;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link ExitCount}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ExitCountTest {

    /**
     * Activates on limit.
     */
    @Test
    public void activatesOnLimit() {
        final long limit = 100L;
        final Exit exit = new ExitCount(limit);
        for (long count = 1L; count < limit; count++) {
            MatcherAssert.assertThat(exit.active(), Matchers.is(false));
        }
        MatcherAssert.assertThat(exit.active(), Matchers.is(true));
    }

}
