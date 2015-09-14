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
     * Registers entities so that they are rendered with the given
     * {@link EntityRenderer}.
     * @param renderer Entity renderer.
     * @param entities Entities
     */
    <T extends Entity> void register(
        @NotNull EntityRenderer<T> renderer, @NotNull T... entities
    );

    /**
     * Renders a frame.
     */
    void render();

}
