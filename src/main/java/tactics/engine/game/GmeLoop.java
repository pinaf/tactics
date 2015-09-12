package tactics.engine.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import tactics.engine.entity.Entity;

/**
 * An implementation of {@link Game} that works with a simple loop.
 * @author Felipe Pina (felipe.pina@protonmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class GmeLoop implements Game {

    /**
     * Entities.
     */
    private final transient Collection<Entity> entities
        = new ArrayList<>(100 * 1000);

    /**
     * Total number of cycles run so far.
     */
    private transient long cycles;

    @Override
    public void start(@NotNull final Exit exit) {
        while (!exit.active()) {
            this.runCycle();
        }
    }

    @SuppressWarnings("OverloadedVarargsMethod")
    @Override
    public GmeLoop with(@NotNull final Entity... entts) {
        this.entities.addAll(Arrays.asList(entts));
        return this;
    }

    @Override
    public void runCycle() {
        for (final Entity entity: this.entities) {
            entity.act();
        }
        this.cycles++;
    }

    @Override
    public long cycles() {
        return this.cycles;
    }

}
