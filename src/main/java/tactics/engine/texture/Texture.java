package tactics.engine.texture;

/**
 * A texture.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Texture {

    /**
     * Returns this texture's width.
     * @return Width.
     */
    int width();

    /**
     * Returns this texture's height.
     * @return Height.
     */
    int height();

    /**
     * Loads this texture for rendering. Should be called every time it will
     * be rendered.
     */
    void load();

    /**
     * Draws this texture on the given coordinates.
     * @param xcoord X coordinate.
     * @param ycoord Y coordinate.
     */
    void draw(float xcoord, float ycoord);

}
