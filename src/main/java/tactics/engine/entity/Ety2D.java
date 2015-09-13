package tactics.engine.entity;

import javax.validation.constraints.NotNull;
import tactics.engine.space.Direction2D;
import tactics.engine.space.Vector2D;

/**
 * A {@link Entity} in (2D) space.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Ety2D<T extends Number> extends Entity {

    /**
     * Current position.
     * @return Current position.
     */
    Vector2D<T> pos();

    /**
     * Moves the entity in a given direction.
     * @param direction Direciton.
     */
    void move(@NotNull Direction2D direction);

}
