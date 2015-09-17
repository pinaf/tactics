package tactics.engine.tile;

import javax.validation.constraints.NotNull;
import tactics.engine.render.EntityRenderer;
import tactics.engine.sprite.EtyRndrSprite;

/**
 * tactics-server.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
@SuppressWarnings("InstanceVariableOfConcreteClass")
public final class EtyRndrTile implements EntityRenderer<EtyTile> {

    /**
     * Screen width.
     */
    private final transient int width;

    /**
     * Screen height.
     */
    private final transient int height;

    /**
     * Renderer.
     */
    private final transient EtyRndrSprite renderer;

    /**
     * Ctor.
     * @param wdth Screen width.
     * @param hght Screen height.
     */
    public EtyRndrTile(final int wdth, final int hght) {
        this.width = wdth;
        this.height = hght;
        this.renderer = new EtyRndrSprite(wdth, hght);
    }

    @Override
    public void render(@NotNull final EtyTile entity) {
        this.renderer.render(entity);
    }

}
