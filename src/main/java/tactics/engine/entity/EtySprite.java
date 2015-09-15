package tactics.engine.entity;

import tactics.engine.sprite.Sprite;

/**
 * An entity that is also a sprite.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface EtySprite<T extends Number> extends Ety2D<T> {

    /**
     * Retrieves the {@link Sprite}.
     * @return Sprite.
     */
    Sprite sprite();

}
