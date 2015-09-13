package tactics.game;

import tactics.engine.game.GmeLoop;
import tactics.engine.render.EtyRndSquare;
import tactics.engine.render.Renderer;
import tactics.engine.render.RndrLWJGL;

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
    private static final int WIDTH = 121;

    /**
     * Screen height.
     */
    private static final int HEIGHT = 121;

    /**
     * Character.
     */
    private final transient EtyCharacter character;

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
        this.with(this.character);
        this.renderer = new RndrLWJGL(GmeTactics.WIDTH, GmeTactics.HEIGHT);
    }

    @Override
    public void init() {
        super.init();
        this.renderer.register(
            this.character,
            new EtyRndSquare(GmeTactics.WIDTH, GmeTactics.HEIGHT, 2.0f)
        );
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
