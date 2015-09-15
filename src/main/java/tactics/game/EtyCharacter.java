package tactics.game;

import javax.validation.constraints.NotNull;
import tactics.engine.entity.EtySprite;
import tactics.engine.space.Direction2D;
import tactics.engine.space.V2Integer;
import tactics.engine.space.Vector2D;
import tactics.engine.sprite.Sprite;

/**
 * A character {@link tactics.engine.entity.Entity}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class EtyCharacter implements EtySprite<Integer> {

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
    public EtyCharacter(@NotNull final Sprite sprt) {
        this(new V2Integer(), sprt);
    }

    /**
     * Ctor.
     * @param pos Starting position.
     * @param sprt Sprite.
     */
    public EtyCharacter(@NotNull final Vector2D<Integer> pos,
        @NotNull final Sprite sprt) {
        this.position = pos;
        this.sprite = sprt;
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
    public Sprite sprite() {
        return this.sprite;
    }

    @Override
    public void move(@NotNull final Direction2D direction) {
        this.position.translate(direction);
    }

    @Override
    public void act() {
    }

}
