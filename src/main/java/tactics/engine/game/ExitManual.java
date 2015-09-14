package tactics.engine.game;

/**
 * Implementation of {@link Exit} that can only triggered manually.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ExitManual implements Exit {

    /**
     * Halt flag.
     */
    private transient boolean halt;

    /**
     * Ctor.
     */
    public ExitManual() {
        this.halt = false;
    }

    @Override
    public boolean active() {
        return this.halt;
    }

    @Override
    public void activate() {
        this.halt = true;
    }

}
