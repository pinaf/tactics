package tactics.engine.render;

import javax.validation.constraints.NotNull;
import org.lwjgl.opengl.GL11;
import tactics.engine.entity.EtySprite;
import tactics.engine.sprite.Sprite;

/**
 * An entity render for {@link EtySprite}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class EtyRndrSprite implements EntityRenderer<EtySprite<Integer>> {

    /**
     * Screen width.
     */
    private final transient int width;

    /**
     * Screen height.
     */
    private final transient int height;

    /**
     * Ctor.
     * @param wdth Screen width.
     * @param hght Screen height.
     */
    public EtyRndrSprite(final int wdth, final int hght) {
        this.width = wdth;
        this.height = hght;
    }

    @Override
    public void render(@NotNull final EtySprite<Integer> entity) {
        final Sprite sprite = entity.sprite();
        sprite.load();
        final float xcoord = (float) this.width / 2.0f + entity.pos().coordX();
        final float ycoord = (float) this.height / 2.0f + entity.pos().coordY();
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex2f(xcoord, ycoord);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex2f(xcoord, ycoord + (float) sprite.height());
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex2f(
            xcoord + (float) sprite.width(),
            ycoord + (float) sprite.height()
        );
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex2f(xcoord + (float) sprite.width(), ycoord);
        GL11.glEnd();
    }

}
