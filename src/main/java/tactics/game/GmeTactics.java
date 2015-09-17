package tactics.game;

import java.io.IOException;
import org.lwjgl.glfw.GLFW;
import tactics.engine.entity.EtyGroup;
import tactics.engine.entity.EtySprite;
import tactics.engine.game.GmeLoop;
import tactics.engine.input.KeybLWJGL;
import tactics.engine.render.EtyRndrGroup;
import tactics.engine.render.EtyRndrSprite;
import tactics.engine.render.RndrLWJGL;
import tactics.engine.space.Direction2D;
import tactics.engine.space.V2Integer;
import tactics.engine.sprite.SprtLWJGL;
import tactics.engine.tile.EtyTile;

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
    private transient EtyCharacter character;

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
        this.keyboard = new KeybLWJGL();
        this.renderer = new RndrLWJGL(
            "Tactics v0.1", GmeTactics.WIDTH, GmeTactics.HEIGHT
        );
    }

    @Override
    public void init() {
        super.init();
        this.renderer.init();
        this.keyboard.init();
        this.renderer.withKeyboard(this.keyboard);
        try {
            this.character = new EtyCharacter(new SprtLWJGL("img/man1.gif"));
            final EtyCharacter second = new EtyCharacter(
                new V2Integer(-GmeTactics.WIDTH / 4, GmeTactics.HEIGHT / 4),
                new SprtLWJGL("img/man2.gif")
            );
            this.with(this.character, second);
            final EtyGroup<EtySprite<Integer>> background = new EtyGroup<>();
            final int left = -GmeTactics.WIDTH / 2;
            final int bottom = -GmeTactics.HEIGHT / 2;
            for (int row = 0; row < 25; row++) {
                for (int col = 0; col < 38; col++) {
                    background.with(
                        new EtyTile(
                            new V2Integer(left + 32 * col, bottom + 32 * row),
                            new SprtLWJGL("img/chess_white.png")
                        )
                    );
                }
            }
            this.renderer.register(
                new EtyRndrGroup<>(
                    new EtyRndrSprite(GmeTactics.WIDTH, GmeTactics.HEIGHT)
                ),
                background
            );
            this.renderer.register(
                new EtyRndrSprite(GmeTactics.WIDTH, GmeTactics.HEIGHT),
                this.character, second
            );
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();
        this.keyboard.shutdown();
        this.renderer.shutdown();
    }

    @Override
    public boolean runCycle() {
        if (!super.runCycle() || this.keyboard.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            return false;
        }
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
        return true;
    }

}
