package tactics.engine.render;

import javax.validation.constraints.NotNull;
import tactics.engine.entity.Entity;

/**
 * Renders a specific entity type.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface EntityRenderer<T extends Entity> {

    /**
     * Renders the given entity.
     */
    void render(@NotNull T entity);

}
