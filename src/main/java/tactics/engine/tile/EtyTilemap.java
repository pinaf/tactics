package tactics.engine.tile;

import java.util.Iterator;
import javax.validation.constraints.NotNull;
import tactics.engine.entity.Entity;
import tactics.engine.entity.EtyGroup;
import tactics.engine.entity.EtyGrpSimple;

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
