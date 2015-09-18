package tactics.engine.sprite;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * A simple implementation of {@link SpriteCache}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class SprCacheSimple implements SpriteCache {

    /**
     * Sprites.
     */
    private final transient List<Sprite> sprites = new ArrayList<>(100);

    @Override
    public int add(@NotNull final Sprite sprite) {
        final int index = this.sprites.size();
        this.sprites.add(sprite);
        return index;
    }

    @Override
    public Sprite sprite(final int index) {
        if (index < 0 || index >= this.sprites.size()) {
            throw new IllegalArgumentException(
                String.format("Invalid index: %d", index)
            );
        }
        return this.sprites.get(index);
    }

}
