package tactics.game;

import tactics.engine.game.ExitManual;
import tactics.engine.game.Game;
import tactics.engine.game.GmeClocked;

/**
 * Entry point.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
@SuppressWarnings("UtilityClass")
public final class Entry {

    /**
     * Ctor.
     */
    private Entry() {
        // empty
    }

    /**
     * Main entry point.
     * @param args Program arguments.
     */
    public static void main(final String[] args) {
        final Game game = new GmeTactics();
        new GmeClocked(120.0, game).start(new ExitManual());
    }

}
