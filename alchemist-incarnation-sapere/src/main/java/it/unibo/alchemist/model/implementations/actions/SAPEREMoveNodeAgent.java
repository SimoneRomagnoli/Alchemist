/*
 * Copyright (C) 2010-2019, Danilo Pianini and contributors listed in the main project's alchemist/build.gradle file.
 *
 * This file is part of Alchemist, and is distributed under the terms of the
 * GNU General Public License, with a linking exception,
 * as described in the file LICENSE in the Alchemist distribution's top directory.
 */
package it.unibo.alchemist.model.implementations.actions;

import it.unibo.alchemist.model.interfaces.Environment;
import it.unibo.alchemist.model.interfaces.EuclideanEnvironment;
import it.unibo.alchemist.model.interfaces.ILsaMolecule;
import it.unibo.alchemist.model.interfaces.ILsaNode;
import it.unibo.alchemist.model.interfaces.Neighborhood;
import it.unibo.alchemist.model.interfaces.Node;
import it.unibo.alchemist.model.interfaces.Position;

import java.util.List;


/**
 * @param <P> {@link Position} type
 */
public abstract class SAPEREMoveNodeAgent<P extends Position<? extends P>> extends SAPERELocalAgent {

    private static final long serialVersionUID = 1202282862490424016L;
    private final Environment<List<ILsaMolecule>, P> environment;

    /**
     * Creates a new SAPERE Local Agent stub. If you use this constructor, you
     * must be sure that your agent does not modify any molecule (e.g. an agent
     * that just moves a node).
     * 
     * @param environment
     *            The current environment
     * @param node
     *            The node in which this agent stays
     */
    public SAPEREMoveNodeAgent(final Environment<List<ILsaMolecule>, P> environment, final ILsaNode node) {
        super(node);
        this.environment = environment;
    }

    /**
     * Creates a new SAPERE Local Agent stub. Use this constructor if you agent
     * modifies a molecule (locally!)
     * 
     * @param environment
     *            The current environment
     * @param node
     *            The node in which this agent stays
     * @param molecule
     *            The modified molecule template
     */
    public SAPEREMoveNodeAgent(
            final Environment<List<ILsaMolecule>, P> environment,
            final ILsaNode node,
            final ILsaMolecule molecule
    ) {
        super(node, molecule);
        this.environment = environment;
    }

    /**
     * @return the environment
     */
    protected Environment<List<ILsaMolecule>, P> getEnvironment() {
        return environment;
    }

    /**
     * @return the current position of the node
     */
    protected P getCurrentPosition() {
        return environment.getPosition(getNode());
    }

    /**
     * @param node
     *            the node
     * @return the position of node
     */
    protected final P getPosition(final Node<List<ILsaMolecule>> node) {
        return environment.getPosition(node);
    }

    /**
     * @param node
     *            the node
     * @return the position of node
     */
    protected final Neighborhood<List<ILsaMolecule>> getNeighborhood(final ILsaNode node) {
        return environment.getNeighborhood(node);
    }

    /**
     * @return the position of node
     */
    protected final Neighborhood<List<ILsaMolecule>> getLocalNeighborhood() {
        return environment.getNeighborhood(getNode());
    }

    /**
     * @param direction
     *            the point towards which move the node
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected final void move(final P direction) {
        if (environment instanceof EuclideanEnvironment) {
            ((EuclideanEnvironment) environment).moveNode(getNode(), direction);
        } else {
            final var myPosition = environment.getPosition(getNode());
            environment.moveNodeToPosition(getNode(), myPosition.plus(direction.getCoordinates()));
        }
    }
}
