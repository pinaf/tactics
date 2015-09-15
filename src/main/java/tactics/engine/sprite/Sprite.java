package tactics.engine.sprite;

/**
 * A Sprite.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Sprite {

    /**
     * Returns this sprite's width.
     * @return Width.
     */
    int width();

    /**
     * Returns this sprite's height.
     * @return Height.
     */
    int height();

    /**
     * Loads this sprite for rendering. Should be called every time it is
     * rendered.
     */
    void load();

}
