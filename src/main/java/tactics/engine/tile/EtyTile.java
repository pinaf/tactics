package tactics.engine.tile;

import javax.validation.constraints.NotNull;
import tactics.engine.entity.EtySprite;
import tactics.engine.space.Direction2D;
import tactics.engine.space.V2Integer;
import tactics.engine.space.Vector2D;
import tactics.engine.sprite.Sprite;

/**
 * A Tile {@link tactics.engine.entity.Entity}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class EtyTile implements EtySprite<Integer> {

    /**
     * Current position.
     */
    private final transient Vector2D<Integer> position;

    /**
     * Sprite.
     */
    private final transient Sprite sprite;

    /**
     * Ctor.
     * @param sprt Sprite.
     */
    public EtyTile(@NotNull final Sprite sprt) {
        this(new V2Integer(), sprt);
    }

    /**
     * Ctor.
     * @param pos Starting position.
     * @param sprt Sprite.
     */
    public EtyTile(@NotNull final Vector2D<Integer> pos,
        @NotNull final Sprite sprt) {
        this.position = pos;
        this.sprite = sprt;
    }

    @Override
    public Sprite sprite() {
        return this.sprite;
    }

    @Override
    public void act() {
        // empty
    }

    @Override
    public Vector2D<Integer> pos() {
        return this.position;
    }

    @Override
    public void move(@NotNull final Direction2D direction) {
        // empty
    }

}
