/*
 * Copyright (C) 2010-2020, Danilo Pianini and contributors
 * listed in the main project's alchemist/build.gradle.kts file.
 *
 * This file is part of Alchemist, and is distributed under the terms of the
 * GNU General Public License, with a linking exception,
 * as described in the file LICENSE in the Alchemist distribution's top directory.
 */

package it.unibo.alchemist.boundary.interfaces;

import it.unibo.alchemist.boundary.wormhole.interfaces.Wormhole2D;
import it.unibo.alchemist.model.interfaces.Position2D;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import javafx.scene.canvas.GraphicsContext;

/**
 * Functional interface that models a command for
 * JavaFX thread to draw something on a {@link javafx.scene.canvas.Canvas}.
 *
 * @param <P> The position type
 */
@FunctionalInterface
public interface DrawCommand<P extends Position2D<? extends P>>
        extends BiConsumer<GraphicsContext, Wormhole2D<P>> {

    /**
     * The method consumes a graphic and a wormhole to draw something.
     *
     * @param graphic  the {@link GraphicsContext} of a JavaFX {@link javafx.scene.canvas.Canvas}
     * @param wormhole the {@link Wormhole2D Wormhole} that maps
     * {@link it.unibo.alchemist.model.interfaces.Environment}
     * {@link it.unibo.alchemist.model.interfaces.Position positions}
     *                 to GUI positions
     */
    @Override
    void accept(GraphicsContext graphic, Wormhole2D<P> wormhole);

    /**
     * Wrapper method that wraps this {@link DrawCommand} into another that checks
     * if should execute or not the {@link #accept(GraphicsContext, Wormhole2D)} method.
     *
     * @param booleanSupplier a condition checker {@link Boolean} {@link Supplier}
     * @return a new {@link DrawCommand} that wraps this one around the if checking
     */
    default DrawCommand<P> wrap(final Supplier<Boolean> booleanSupplier) {
        return (g, wh) -> {
            if (booleanSupplier.get()) {
                this.accept(g, wh);
            }
        };
    }

}
