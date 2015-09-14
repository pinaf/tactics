package tactics.game;

import javax.validation.constraints.NotNull;
import tactics.engine.entity.Ety2D;
import tactics.engine.space.Direction2D;
import tactics.engine.space.V2Integer;
import tactics.engine.space.Vector2D;

/**
 * A character {@link tactics.engine.entity.Entity}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class EtyCharacter implements Ety2D<Integer> {

    /**
     * Current position.
     */
    private final transient Vector2D<Integer> position;

    /**
     * Ctor.
     */
    public EtyCharacter() {
        this(new V2Integer());
    }

    /**
     * Ctor.
     * @param pos Starting position.
     */
    public EtyCharacter(@NotNull final Vector2D<Integer> pos) {
        this.position = pos;
    }

    /**
     * Returns this character's name.
     * @return Name.
     */
    public String name() {
        return "Dummy";
    }

    @Override
    public Vector2D<Integer> pos() {
        return this.position;
    }

    @Override
    public void move(@NotNull final Direction2D direction) {
        this.position.translate(direction);
    }

    @Override
    public void act() {
    }

}
