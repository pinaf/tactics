package tactics.engine.space;

import javax.validation.constraints.NotNull;

/**
 * 2D vector.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Vector2D<T extends Number> {

    /**
     * X coordinate.
     * @return X coordinate.
     */
    T coordX();

    /**
     * Y coordinate.
     * @return Y coordinate.
     */
    T coordY();

    /**
     * Translates this vector.
     * @param trans Translation vector.
     */
    void translate(@NotNull Vector2D<T> trans);

}
