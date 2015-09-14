package tactics.game;

import tactics.engine.game.GmeLoop;
import tactics.engine.render.EtyRndSquare;
import tactics.engine.render.Renderer;
import tactics.engine.render.RndrLWJGL;
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
    private final transient Renderer renderer;

    /**
     * Ctor.
     */
    public GmeTactics() {
        super();
        this.character = new EtyCharacter();
        this.second = new EtyCharacter(
            new V2Integer(- GmeTactics.WIDTH / 4, GmeTactics.HEIGHT / 4)
        );
        this.with(this.character, this.second);
        this.renderer = new RndrLWJGL(
            "Tactics v0.1", GmeTactics.WIDTH, GmeTactics.HEIGHT
        );
    }

    @Override
    public void init() {
        super.init();
        final EtyRndSquare chars = new EtyRndSquare(
            GmeTactics.WIDTH, GmeTactics.HEIGHT, 2.0f
        );
        this.renderer.register(chars, this.character, this.second);
        this.renderer.init();
    }

    @Override
    public void shutdown() {
        super.shutdown();
        this.renderer.shutdown();
    }

    @Override
    public void runCycle() {
        super.runCycle();
        if (this.cycles() % 10L == 0L) {
            System.out.println(
                String.format(
                    "%s: %s",
                    this.character.name(),
                    this.character.pos().toString()
                )
            );
        }
        this.renderer.render();
    }

}
