package tactics.engine.render;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.lwjgl.Sys;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import tactics.engine.entity.Entity;

/**
 * A LWJGL backed implementation of {@link Renderer}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RndrLWJGL implements Renderer {

    /**
     * Screen width.
     */
    private final transient int width;

    /**
     * Screen height.
     */
    private final transient int height;

    /**
     * Entity to renderer map.
     */
    private final transient Map<Entity, EntityRenderer> entities;

    /**
     * Error callback.
     */
    private transient GLFWErrorCallback errors;

    /**
     * Keys callback.
     */
    private transient GLFWKeyCallback keys;

    /**
     * The window handle
     */
    private transient long window;

    /**
     * Ctor.
     * @param wdth Screen width.
     * @param hegt Screen height.
     */
    public RndrLWJGL(final int wdth, final int hegt) {
        this.width = wdth;
        this.height = hegt;
        this.entities = new HashMap<>(100 * 1000);
    }

    @Override
    public <T extends Entity> void register(
        @NotNull final T entity, @NotNull final EntityRenderer<T> renderer) {
        this.entities.put(entity, renderer);
    }

    @Override
    public void render() {
        if (GLFW.glfwWindowShouldClose(this.window) == GL11.GL_FALSE) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            for (final Map.Entry<Entity, EntityRenderer> entry
                    : this.entities.entrySet()) {
                entry.getValue().render(entry.getKey());
            }
            GLFW.glfwSwapBuffers(this.window);
            GLFW.glfwPollEvents();
        }
    }

    @Override
    public void init() {
        System.out.printf("Hello LWJGL %s!%n", Sys.getVersion());
        this.errors = Callbacks.errorCallbackPrint(System.err);
        GLFW.glfwSetErrorCallback(this.errors);
        if (GLFW.glfwInit() != GL11.GL_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
        this.window = GLFW.glfwCreateWindow(
            this.width, this.height, "Hello World!", 0L, 0L
        );
        if (this.window == 0L) {
            throw new IllegalStateException("Failed to create the GFW window");
        }
        this.keys = new GLFWKeyCallback() {
            @Override
            public void invoke(
                final long win, final int key, final int scancode,
                final int action, final int mods) {
                if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
                    // We will detect this in our rendering loop
                    GLFW.glfwSetWindowShouldClose(win, GL11.GL_TRUE);
                }
            }
        };
        GLFW.glfwSetKeyCallback(this.window, this.keys);
        final ByteBuffer vidmode = GLFW
            .glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(
            this.window,
            (GLFWvidmode.width(vidmode) - this.width) / 2,
            (GLFWvidmode.height(vidmode) - this.height) / 2
        );
        GLFW.glfwMakeContextCurrent(this.window);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(this.window);
        GLContext.createFromCurrent();
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(
            0.0, (double) this.width,
            0.0, (double) this.height,
            1.0, -1.0
        );
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    @Override
    public void shutdown() {
        System.out.printf("Goodbye LWJGL %s!%n", Sys.getVersion());
        try {
            GLFW.glfwDestroyWindow(this.window);
            this.keys.release();
        } finally {
            GLFW.glfwTerminate();
            this.errors.release();
        }
    }

}
