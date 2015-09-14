package tactics.engine.game;

import javax.validation.constraints.NotNull;

/**
 * A wrapper for {@link Exit}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ExitWrap implements Exit {

    /**
     * Wrapped exit.
     */
    private final transient Exit wrapped;

    /**
     * Ctor.
     * @param wrpd Wrapped exit.
     */
    public ExitWrap(@NotNull final Exit wrpd) {
        this.wrapped = wrpd;
    }

    @Override
    public boolean active() {
        return this.wrapped.active();
    }

    @Override
    public void activate() {
        this.wrapped.activate();
    }

}
