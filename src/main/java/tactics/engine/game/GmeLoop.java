package tactics.engine.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import tactics.engine.entity.Entity;

/**
 * An implementation of {@link Game} that works with a simple loop.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public class GmeLoop implements Game {

    /**
     * Entities.
     */
    private final transient Collection<Entity> entities
        = new ArrayList<>(100 * 1000);

    /**
     * Total number of cycles run so far.
     */
    private transient long cycles;

    /**
     * Exit condition.
     */
    private transient Optional<Exit> exit = Optional.empty();

    /**
     * Ctor.
     */
    public GmeLoop() {
        this.cycles = 0L;
    }

    @Override
    public void init() {
        // empty
    }

    @Override
    public void shutdown() {
        // empty
    }

    @Override
    public final void start(@NotNull final Exit ext) {
        this.exit = Optional.of(ext);
        this.init();
        while (!ext.active()) {
            if (!this.runCycle()) {
                this.exit.get().activate();
            }
        }
        this.shutdown();
    }

    @Override
    public final void stop() {
        if (this.exit.isPresent()) {
            this.exit.get().activate();
        }
    }

    @SuppressWarnings("OverloadedVarargsMethod")
    @Override
    public final GmeLoop with(@NotNull final Entity... entts) {
        this.entities.addAll(Arrays.asList(entts));
        return this;
    }

    @Override
    public boolean runCycle() {
        for (final Entity entity: this.entities) {
            entity.act();
        }
        this.cycles++;
        return true;
    }

    @Override
    public final long cycles() {
        return this.cycles;
    }

}
