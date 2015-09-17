package tactics.engine.render;

import javax.validation.constraints.NotNull;
import tactics.engine.entity.Entity;
import tactics.engine.entity.EtyGroup;

/**
 * Entity renderer for an {@link EtyGroup}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class EtyRndrGroup<T extends Entity>
    implements EntityRenderer<EtyGroup<T>> {

    /**
     * Wrapped renderer.
     */
    private final transient EntityRenderer<T> renderer;

    /**
     * Ctor.
     * @param rndr Renderer.
     */
    public EtyRndrGroup(final EntityRenderer<T> rndr) {
        this.renderer = rndr;
    }

    @Override
    public void render(@NotNull final EtyGroup<T> group) {
        for (final T entity : group) {
            this.renderer.render(entity);
        }
    }

}
