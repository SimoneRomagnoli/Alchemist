/*
 * Copyright (C) 2010-2019, Danilo Pianini and contributors listed in the main project's alchemist/build.gradle file.
 *
 * This file is part of Alchemist, and is distributed under the terms of the
 * GNU General Public License, with a linking exception,
 * as described in the file LICENSE in the Alchemist distribution's top directory.
 */
package it.unibo.alchemist.model.interfaces.movestrategies;

import java.io.Serializable;

import it.unibo.alchemist.model.interfaces.Node;
import it.unibo.alchemist.model.interfaces.Position;
import it.unibo.alchemist.model.interfaces.Reaction;
import it.unibo.alchemist.model.interfaces.Route;

/**
 * Strategy interface describing how the routing between two points happens.
 *
 * @param <T> Concentration type
 * @param <P> position type
 */
@FunctionalInterface
public interface RoutingStrategy<T, P extends Position<P>> extends Serializable {

    /**
     * Computes a route between two positions.
     * 
     * @param currentPos starting {@link Position}
     * @param finalPos ending {@link Position}
     * @return a {@link Route} connecting the two points
     */
    Route<P> computeRoute(P currentPos, P finalPos);

    /**
     * @param destination the {@link Node} where the strategy is being cloned
     * @param reaction the {@link Reaction} where strategy is being cloned
     *
     * @return A copy of the strategy if the strategy is stateful, and this object otherwise.
     * The default implementation assumes a stateless strategy.
     */
    default RoutingStrategy<T, P> cloneIfNeeded(final Node<T> destination, final Reaction<T> reaction) {
        return this;
    }

}
