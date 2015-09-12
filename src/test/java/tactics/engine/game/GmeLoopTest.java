package tactics.engine.game;

import org.junit.Test;
import org.mockito.Mockito;
import tactics.engine.entity.Entity;

/**
 * Tests for {@link GmeLoop}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class GmeLoopTest {

    /**
     * Allows entities to act.
     */
    @Test
    public void allowsEntitiesToAct() {
        final Entity first = Mockito.mock(Entity.class);
        final Entity second = Mockito.mock(Entity.class);
        final Entity third = Mockito.mock(Entity.class);
        final int times = 10;
        final Game game = new GmeLoop().with(first, third);
        game.start(new ExitCount((long) times, game));
        Mockito.verify(first, Mockito.times(times)).act();
        Mockito.verifyZeroInteractions(second);
        Mockito.verify(third, Mockito.times(times)).act();
    }

}
