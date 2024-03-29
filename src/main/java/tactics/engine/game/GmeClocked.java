package tactics.engine.game;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import tactics.engine.entity.Entity;

/**
 * A {@link Game} decorator that runs according to a clock.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class GmeClocked implements Game {

    /**
     * Target rate (in Hz).
     */
    private final transient double target;

    /**
     * Wrapped {@link Game}.
     */
    private final transient Game game;

    /**
     * Exit condition.
     */
    private transient Optional<Exit> exit = Optional.empty();

    /**
     * Ctor.
     * @param tgt Target rate (in Hz).
     * @param gme Wrapped {@link Game}.
     */
    public GmeClocked(final double tgt, @NotNull final Game gme) {
        this.target = tgt;
        this.game = gme;
    }

    @Override
    public void init() {
        this.game.init();
    }

    @Override
    public void shutdown() {
        this.game.shutdown();
    }

    @Override
    public void start(@NotNull final Exit ext) {
        this.exit = Optional.of(ext);
        this.init();
        long last = System.nanoTime();
        while (!ext.active()) {
            final long now = System.nanoTime();
            final int cycles = (int)
                ((double) (now - last) / 1.0E9 * this.target);
            if (cycles < 5) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10L);
                } catch (final InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            } else {
                last = now;
                for (int idx = 0; idx < cycles; idx++) {
                    if (!this.game.runCycle()) {
                        this.exit.get().activate();
                        break;
                    }
                }
            }
        }
        this.shutdown();
    }

    @Override
    public void stop() {
        if (this.exit.isPresent()) {
            this.exit.get().activate();
        }
    }

    @SuppressWarnings("OverloadedVarargsMethod")
    @Override
    public Game with(@NotNull final Entity... entities) {
        return this.game.with(entities);
    }

    @Override
    public boolean runCycle() {
        return this.game.runCycle();
    }

    @Override
    public long cycles() {
        return this.game.cycles();
    }

}
