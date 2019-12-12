/*
 * Copyright (C) 2010-2019, Danilo Pianini and contributors listed in the main project's alchemist/build.gradle file.
 *
 * This file is part of Alchemist, and is distributed under the terms of the
 * GNU General Public License, with a linking exception,
 * as described in the file LICENSE in the Alchemist distribution's top directory.
 */
package it.unibo.alchemist.loader.displacements

import it.unibo.alchemist.model.interfaces.Environment
import it.unibo.alchemist.model.interfaces.Position

/**
 * Given an environment and a list of list of numbers, it creates a list of the right position type for the environment.
 */
class SpecificPositions(
    environment: Environment<*, *>,
    vararg positions: Iterable<Number>
) : Displacement<Position<*>> {

    private val positions: List<Position<*>> = positions.map { environment.makePosition(*it.toList().toTypedArray()) }

    override fun stream() = positions.stream()
}
