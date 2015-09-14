package tactics.engine.game;

import javax.validation.constraints.NotNull;

/**
 * An {@link Exit} that activates after a given number of cycles are run.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ExitCount extends ExitWrap {

    /**
     * Number of calls before activation.
     */
    private final transient long limit;

    /**
     * Game.
     */
    private final transient Game game;

    /**
     * Ctor.
     * @param lmt Number of calls before activation.
     */
    public ExitCount(final long lmt, @NotNull final Game gme) {
        super(new ExitManual());
        this.limit = lmt;
        this.game = gme;
    }

    @Override
    public boolean active() {
        return this.game.cycles() >= this.limit || super.active();
    }

}
