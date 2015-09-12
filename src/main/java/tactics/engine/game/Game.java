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
     * Adds an entity to this game.
     * @param entity Entity.
     * @return Itself.
     */
    Game with(@NotNull Entity entity);

    /**
     * Starts the game.
     */
    void start();

}
