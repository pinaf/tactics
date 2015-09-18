package tactics.engine.tile;

import java.util.Iterator;
import javax.validation.constraints.NotNull;
import tactics.engine.entity.Entity;
import tactics.engine.entity.EtyGroup;
import tactics.engine.entity.EtyGrpSimple;
import tactics.engine.space.V2Integer;
import tactics.engine.sprite.Sprite;
import tactics.engine.sprite.SpriteCache;

/**
 * A tilemap {@link Entity}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class EtyTilemap implements EtyGroup<EtyTile> {

    /**
     * Group.
     */
    private final transient EtyGroup<EtyTile> group;

    /**
     * Ctor.
     */
    public EtyTilemap() {
        this(new EtyTile[0][0]);
    }

    /**
     * Ctor.
     * @param cache Sprite cache.
     * @param tls Tiles.
     */
    public EtyTilemap(@NotNull final SpriteCache cache,
        @NotNull final int[][] tls, final int left, final int bottom,
        final int width, final int height) {
        final int rows = tls.length;
        int columns = 0;
        if (rows > 0) {
            columns = tls[0].length;
        }
        final EtyTile[] all = new EtyTile[rows * columns];
        int idx = 0;
        for (int row = 0; row < rows; row++) {
            if (tls[row].length != columns) {
                throw new IllegalArgumentException(
                    String.format(
                        "Malformed tilemap: expected %d columns at row %d",
                        columns,
                        row
                    )
                );
            }
            for (int col = 0; col < columns; col++) {
                final Sprite sprite = cache.sprite(tls[row][col]);
                if (sprite.width() != width) {
                    throw new IllegalArgumentException(
                        String.format(
                            "Invalid sprite width: is %d but should be %d",
                            sprite.width(),
                            width
                        )
                    );
                }
                if (sprite.height() != height) {
                    throw new IllegalArgumentException(
                        String.format(
                            "Invalid sprite height: is %d but should be %d",
                            sprite.height(),
                            height
                        )
                    );
                }
                all[idx] = new EtyTile(
                    new V2Integer(
                        left + width * col,
                        bottom + sprite.height() * row
                    ),
                    sprite
                );
                idx++;
            }
        }
        this.group = new EtyGrpSimple<>(all);
    }

    /**
     * Ctor.
     * @param tls Tiles.
     */
    public EtyTilemap(@NotNull final EtyTile[][] tls) {
        final int rows = tls.length;
        int columns = 0;
        if (rows > 0) {
            columns = tls[0].length;
            for (int row = 1; row < rows; row++) {
                if (tls[row].length != columns) {
                    throw new IllegalArgumentException(
                        String.format(
                            "Malformed tilemap: expected %d columns at row %d",
                            columns,
                            row
                        )
                    );
                }
            }
        }
        final EtyTile[] all = new EtyTile[rows * columns];
        this.group = new EtyGrpSimple<>(all);
    }

    @Override
    public EtyGroup<EtyTile> with(@NotNull final EtyTile... etts) {
        return this.group.with(etts);
    }

    @Override
    public void act() {
        this.group.act();
    }

    @Override
    public Iterator<EtyTile> iterator() {
        return this.group.iterator();
    }

}
