package tactics.game;

import org.lwjgl.glfw.GLFW;
import tactics.engine.game.GmeLoop;
import tactics.engine.input.KeybLWJGL;
import tactics.engine.render.EtyRndSquare;
import tactics.engine.render.RndrLWJGL;
import tactics.engine.space.Direction2D;
import tactics.engine.space.V2Integer;

/**
 * Tactics {@link tactics.engine.game.Game}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class GmeTactics extends GmeLoop {

    /**
     * Screen width.
     */
    private static final int WIDTH = 1201;

    /**
     * Screen height.
     */
    private static final int HEIGHT = 801;

    /**
     * Character.
     */
    private final transient EtyCharacter character;

    /**
     * Second character.
     */
    private final transient EtyCharacter second;

    /**
     * Renderer.
     */
    private final transient RndrLWJGL renderer;

    /**
     * Keyboard input.
     */
    private final transient KeybLWJGL keyboard;

    /**
     * Ctor.
     */
    public GmeTactics() {
        super();
        this.character = new EtyCharacter();
        this.second = new EtyCharacter(
            new V2Integer(-GmeTactics.WIDTH / 4, GmeTactics.HEIGHT / 4)
        );
        this.with(this.character, this.second);
        this.keyboard = new KeybLWJGL();
        this.renderer = new RndrLWJGL(
            "Tactics v0.1", GmeTactics.WIDTH, GmeTactics.HEIGHT
        );
    }

    @Override
    public void init() {
        super.init();
        this.renderer.register(
            new EtyRndSquare(GmeTactics.WIDTH, GmeTactics.HEIGHT)
                .withColor(0.0f, 0.0f, 1.0f),
            this.character
        );
        this.renderer.register(
            new EtyRndSquare(GmeTactics.WIDTH, GmeTactics.HEIGHT)
                .withColor(1.0f, 0.0f, 0.0f),
            this.second
        );
        this.renderer.init();
        this.keyboard.init();
        this.renderer.withKeyboard(this.keyboard);
    }

    @Override
    public void shutdown() {
        super.shutdown();
        this.keyboard.shutdown();
        this.renderer.shutdown();
    }

    @Override
    public void runCycle() {
        super.runCycle();
        if (this.keyboard.isKeyDown(GLFW.GLFW_KEY_UP)) {
            this.character.move(Direction2D.UP);
        }
        if (this.keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
            this.character.move(Direction2D.DOWN);
        }
        if (this.keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
            this.character.move(Direction2D.LEFT);
        }
        if (this.keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
            this.character.move(Direction2D.RIGHT);
        }
        this.renderer.render();
    }

}
