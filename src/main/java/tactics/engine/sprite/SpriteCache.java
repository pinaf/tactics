package tactics.engine.sprite;

import javax.validation.constraints.NotNull;

/**
 * A mapping of {@link Integer} to {@link Sprite}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface SpriteCache {

    /**
     * Adds a {@link Sprite} to the cache.
     * @param sprite {@link Sprite} to add.
     * @return The index of the {@link Sprite} for future reference.
     */
    int add(@NotNull Sprite sprite);

    /**
     * Returns the {@link Sprite} by its index.
     * @param index Index.
     * @return {@link Sprite}.
     * @throws IllegalArgumentException If the index is invalid.
     */
    Sprite sprite(int index);

}
