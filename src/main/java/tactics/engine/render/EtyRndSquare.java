package tactics.engine.render;

import javax.validation.constraints.NotNull;
import org.lwjgl.opengl.GL11;
import tactics.engine.entity.Ety2D;

/**
 * An {@link EntityRenderer} for instances of {@link Ety2D<Integer>} that
 * renders a square centered on the entities position.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class EtyRndSquare implements EntityRenderer<Ety2D<Integer>> {

    /**
     * Screen width.
     */
    private final transient int width;

    /**
     * Screen height.
     */
    private final transient int height;

    /**
     * Size of the square (half of its side length).
     */
    private final transient float size;

    /**
     * Ctor.
     * @param wdth Screen width.
     * @param hght Screen height.
     * @param sze Size of the square (half of its side length).
     */
    public EtyRndSquare(final int wdth, final int hght, final float sze) {
        this.width = wdth;
        this.height = hght;
        this.size = sze;
    }

    @Override
    public void render(@NotNull final Ety2D<Integer> entity) {
        GL11.glColor3f(0.5f, 0.5f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
        final float xcoord = (float) this.width / 2.0f + entity.pos().coordX();
        final float ycoord = (float) this.height / 2.0f + entity.pos().coordY();
        GL11.glVertex2f(xcoord - this.size, ycoord - this.size);
        GL11.glVertex2f(xcoord - this.size, ycoord + this.size);
        GL11.glVertex2f(xcoord + this.size, ycoord + this.size);
        GL11.glVertex2f(xcoord + this.size, ycoord - this.size);
        GL11.glEnd();
    }

}
