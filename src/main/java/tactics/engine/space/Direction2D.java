package tactics.engine.space;

import javax.validation.constraints.NotNull;

/**
 * A direction.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public enum Direction2D implements Vector2D<Integer> {

    UP(0,1),
    DOWN(0,-1),
    LEFT(-1,0),
    RIGHT(1,0);

    /**
     * X coordinate.
     */
    private final transient int coordx;

    /**
     * Y coordinate.
     */
    private final transient int coordy;

    /**
     * Ctor.
     * @param crdx X coordinate.
     * @param crdy Y coordinate.
     */
    Direction2D(final int crdx, final int crdy) {
        this.coordx = crdx;
        this.coordy = crdy;
    }

    @Override
    public Integer coordX() {
        return this.coordx;
    }

    @Override
    public Integer coordY() {
        return this.coordy;
    }

    @Override
    public void translate(@NotNull final Vector2D<Integer> trans) {
        throw new UnsupportedOperationException("translate");
    }

}
