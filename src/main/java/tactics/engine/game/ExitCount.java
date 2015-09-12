package tactics.engine.game;

/**
 * An {@link Exit} that activates after a given number of calls to
 * {@link Exit#active()}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ExitCount implements Exit {

    /**
     * Number of calls before activation.
     */
    private final transient long limit;

    /**
     * Count for number of calls so far.
     */
    private transient long count;

    /**
     * Ctor.
     * @param lmt Number of calls before activation.
     */
    public ExitCount(final long lmt) {
        this.limit = lmt;
        this.count = 0L;
    }

    @Override
    public boolean active() {
        this.count++;
        return this.count > this.limit;
    }

    /**
     * Returns the number of calls.
     * @return Number of calls.
     */
    public long count() {
        return this.count;
    }

}
