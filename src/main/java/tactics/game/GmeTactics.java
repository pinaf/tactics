package tactics.game;

import tactics.engine.game.GmeLoop;

/**
 * Tactics {@link tactics.engine.game.Game}.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class GmeTactics extends GmeLoop {

    /**
     * Character.
     */
    private final transient EtyCharacter character;

    /**
     * Ctor.
     */
    public GmeTactics() {
        super();
        this.character = new EtyCharacter();
        this.with(this.character);
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
    }

}
