package tactics.engine.game;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

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
        final Game under = Mockito.mock(Game.class);
        Mockito.when(under.cycles()).thenReturn(limit - 1L);
        MatcherAssert.assertThat(
            new ExitCount(limit, under).active(), Matchers.is(false)
        );
        final Game over = Mockito.mock(Game.class);
        Mockito.when(over.cycles()).thenReturn(limit);
        MatcherAssert.assertThat(
            new ExitCount(limit, over).active(), Matchers.is(true)
        );
    }

}
