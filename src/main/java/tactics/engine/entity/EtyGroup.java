package tactics.engine.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Entity that groups other entities.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class EtyGroup<T extends Entity> implements Entity, Iterable<T> {

    /**
     * Entities.
     */
    private final transient List<T> entities;

    /**
     * Ctor.
     */
    public EtyGroup(@NotNull final T... etts) {
        this.entities = new ArrayList<>(Arrays.asList(etts));
    }

    /**
     * Adds entities to this group.
     * @param etts Entities.
     * @return Itself.
     */
    public EtyGroup<T> with(@NotNull final T... etts) {
        this.entities.addAll(Arrays.asList(etts));
        return this;
    }

    @Override
    public void act() {
        for (final T entity : this.entities) {
            entity.act();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.entities.iterator();
    }

}
