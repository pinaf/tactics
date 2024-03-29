package tactics.engine.game;

import javax.validation.constraints.NotNull;
import tactics.engine.Initializable;
import tactics.engine.entity.Entity;

/**
 * A Game.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Game extends Initializable {

    /**
     * Adds entities to this game.
     * @param entities Entities.
     * @return Itself.
     */
    Game with(@NotNull Entity... entities);

    /**
     * Runs a single cycle.
     * @return False iff the game should be stopped.
     */
    boolean runCycle();

    /**
     * Total number of cycles run so far.
     * @return Total number of cycles.
     */
    long cycles();

    /**
     * Starts the game.
     * @param exit Exit condition.
     */
    void start(@NotNull Exit exit);

    /**
     * Stops the game.
     */
    void stop();

}
