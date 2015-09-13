package tactics.engine.space;

import javax.validation.constraints.NotNull;

/**
 * An integer {@link Vector2D}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class V2Integer implements Vector2D<Integer> {

    /**
     * X coordinate.
     */
    private transient int coordx;

    /**
     * Y coordinate.
     */
    private transient int coordy;

    /**
     * Ctor.
     */
    public V2Integer() {
        this(0, 0);
    }

    /**
     * Ctor.
     * @param crdx X coordinate.
     * @param crdy Y coordinate.
     */
    public V2Integer(final int crdx, final int crdy) {
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
        this.coordx += trans.coordX();
        this.coordy += trans.coordY();
    }

    @Override
    public String toString() {
        return String.format("%d,%d", this.coordx, this.coordy);
    }

}
