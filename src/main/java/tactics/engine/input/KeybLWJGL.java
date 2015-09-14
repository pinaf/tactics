package tactics.engine.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * Keyboard input.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class KeybLWJGL extends GLFWKeyCallback implements Keyboard {

    /**
     * Current keyboard state.
     */
    private final transient boolean[] keys = new boolean[65536];

    @Override
    public void init() {
        // empty
    }

    @Override
    public void shutdown() {
        this.release();
    }

    @Override
    public void invoke(final long window, final int key, final int scancode,
        final int action, final int mods) {
        this.keys[key] = action != GLFW.GLFW_RELEASE;
    }

    @Override
    public boolean isKeyDown(final int keycode) {
        return this.keys[keycode];
    }

}
