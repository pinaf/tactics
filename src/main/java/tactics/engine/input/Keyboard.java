package tactics.engine.input;

import tactics.engine.Initializable;

/**
 * Interface for querying the keyboard state.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Keyboard extends Initializable {

    /**
     * Is the given key currently pressed/down?
     * @param keycode Keycode.
     * @return True iff currently pressed/down.
     */
    boolean isKeyDown(int keycode);

}
