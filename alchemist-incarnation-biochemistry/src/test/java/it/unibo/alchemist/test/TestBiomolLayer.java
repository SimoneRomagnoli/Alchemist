/*
 * Copyright (C) 2010-2022, Danilo Pianini and contributors
 * listed, for each module, in the respective subproject's build.gradle.kts file.
 *
 * This file is part of Alchemist, and is distributed under the terms of the
 * GNU General Public License, with a linking exception,
 * as described in the file LICENSE in the Alchemist distribution's top directory.
 */
package it.unibo.alchemist.test;

import it.unibo.alchemist.boundary.interfaces.OutputMonitor;
import it.unibo.alchemist.core.implementations.Engine;
import it.unibo.alchemist.core.interfaces.Simulation;
import it.unibo.alchemist.model.BiochemistryIncarnation;
import it.unibo.alchemist.model.implementations.environments.BioRect2DEnvironment;
import it.unibo.alchemist.model.implementations.layers.StepLayer;
import it.unibo.alchemist.model.implementations.molecules.Biomolecule;
import it.unibo.alchemist.model.implementations.positions.Euclidean2DPosition;
import it.unibo.alchemist.model.implementations.timedistributions.DiracComb;
import it.unibo.alchemist.model.implementations.times.DoubleTime;
import it.unibo.alchemist.model.interfaces.Environment;
import it.unibo.alchemist.model.interfaces.Actionable;
import it.unibo.alchemist.model.interfaces.Layer;
import it.unibo.alchemist.model.interfaces.Molecule;
import it.unibo.alchemist.model.interfaces.Node;
import it.unibo.alchemist.model.interfaces.Reaction;
import it.unibo.alchemist.model.interfaces.Time;
import org.apache.commons.math3.random.MersenneTwister;
import org.jooq.lambda.fi.util.function.CheckedConsumer;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 
 */
class TestBiomolLayer {

    private static final BiochemistryIncarnation INCARNATION = new BiochemistryIncarnation();

    /**
     * Test if cell status is correctly updated in movement.
     */
    @Test
    void testBiomolStepLayer() {
        final Environment<Double, Euclidean2DPosition> environment = new BioRect2DEnvironment(INCARNATION);
        final Biomolecule b = new Biomolecule("B");
        final Layer<Double, Euclidean2DPosition> bLayer = new StepLayer<>(10_000.0, 0d);
        final MersenneTwister rand = new MersenneTwister(0);
        final Node<Double> cellNode = INCARNATION.createNode(rand, environment, null);
        final Molecule a = new Biomolecule("A");
        final Reaction<Double> underTest = INCARNATION.createReaction(
            rand, environment, cellNode,
            INCARNATION.createTimeDistribution(rand, environment, cellNode, "1"),
            "[B in env] --> [A]"
        );
        cellNode.addReaction(underTest);
        cellNode.addReaction(INCARNATION.createReaction(
            rand, environment, cellNode, new DiracComb<>(100d), "[] --> [BrownianMove(10)]"
        ));
        cellNode.setConcentration(a, 0d);
        environment.setLinkingRule(
                new it.unibo.alchemist.model.implementations.linkingrules.ConnectWithinDistance<>(2)
        );
        environment.addNode(cellNode, new Euclidean2DPosition(0, 0));
        environment.addLayer(b, bLayer);
        final Simulation<Double, Euclidean2DPosition> sim = new Engine<>(environment, 3000);
        sim.play();
        sim.addOutputMonitor(new OutputMonitor<>() {
            private static final long serialVersionUID = 0L;

            @Override
            public void stepDone(
                    final Environment<Double, Euclidean2DPosition> environment,
                    final Actionable<Double> reaction,
                    @Nonnull final Time time,
                    final long step
            ) {
                final Euclidean2DPosition curPos = environment.getPosition(environment.getNodeByID(0));
                assertEquals(curPos.getX() > 0 && curPos.getY() > 0, underTest.canExecute());
            }

            @Override
            public void initialized(@Nonnull final Environment<Double, Euclidean2DPosition> environment) {
                stepDone(environment, null, DoubleTime.ZERO, 0);
            }

            @Override
            public void finished(
                    @Nonnull final Environment<Double, Euclidean2DPosition> environment,
                    @Nonnull final Time time,
                    final long step
            ) { }
        });
        sim.run();
        sim.getError().ifPresent(CheckedConsumer.unchecked(it -> {
            throw it;
        }));
    }
}
