package tactics.engine.game;

/**
 * A game exit condition.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Exit {

    /**
     * Exit condition active?
     * @return True iff active.
     */
    boolean active();

    /**
     * Manually activates this exit condition.
     */
    void activate();

}
