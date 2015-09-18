package tactics.engine.entity;

import javax.validation.constraints.NotNull;

/**
 * Entity that groups other entities.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface EtyGroup<T extends Entity> extends Entity, Iterable<T> {

    /**
     * Adds entities to this group.
     * @param etts Entities.
     * @return Itself.
     */
    EtyGroup<T> with(@NotNull T... etts);

}
