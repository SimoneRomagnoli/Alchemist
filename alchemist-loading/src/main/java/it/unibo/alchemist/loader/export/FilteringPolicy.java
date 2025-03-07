/*
 * Copyright (C) 2010-2019, Danilo Pianini and contributors listed in the main project's alchemist/build.gradle file.
 *
 * This file is part of Alchemist, and is distributed under the terms of the
 * GNU General Public License, with a linking exception,
 * as described in the file LICENSE in the Alchemist distribution's top directory.
 */
package it.unibo.alchemist.loader.export;

import java.io.Serializable;

/**
 * Expresses a flat map operation over a double.
 */
@FunctionalInterface
public interface FilteringPolicy extends Serializable {

    /**
     * From a single value, builds a stream of values.
     * 
     * @param value
     *            the input value
     * @return a sequence of double values. In most cases, it will be a
     *         single value, but may easily be an
     *         iterator with no elements (in case the value must be filtered).
     *         Also, the case in which a single value gets mapped onto multiple
     *         values is supported by this interface.
     */
    Iterable<Double> apply(double value);

}
