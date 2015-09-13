package tactics.engine.render;

import javax.validation.constraints.NotNull;
import tactics.engine.Initializable;
import tactics.engine.entity.Entity;

/**
 * Renders frames.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Renderer extends Initializable {

    /**
     * Registers an {@link Entity} so that it is rendered with the given
     * {@link EntityRenderer}.
     * @param entity Entity.
     * @param renderer Entity renderer.
     */
    <T extends Entity> void register(
        @NotNull T entity, @NotNull EntityRenderer<T> renderer
    );

    /**
     * Renders a frame.
     */
    void render();

}
