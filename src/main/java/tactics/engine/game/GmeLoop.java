package tactics.engine.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import tactics.engine.Entity;

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

    @Override
    public void start(@NotNull final Exit exit) {
        while (!exit.active()) {
            this.loop();
        }
    }

    @SuppressWarnings("OverloadedVarargsMethod")
    @Override
    public GmeLoop with(@NotNull final Entity... entts) {
        this.entities.addAll(Arrays.asList(entts));
        return this;
    }

    /**
     * The game loop.
     */
    private void loop() {
        for (final Entity entity: this.entities) {
            entity.act();
        }
    }

}
