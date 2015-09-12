package tactics.engine.game;

import javax.validation.constraints.NotNull;
import tactics.engine.Entity;

/**
 * A Game.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Game {

    /**
     * Adds entities to this game.
     * @param entities Entities.
     * @return Itself.
     */
    Game with(@NotNull Entity... entities);

    /**
     * Starts the game.
     * @param exit Exit condition.
     */
    void start(@NotNull Exit exit);

}
