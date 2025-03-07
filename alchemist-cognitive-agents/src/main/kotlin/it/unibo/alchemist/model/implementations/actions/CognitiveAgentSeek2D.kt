package it.unibo.alchemist.model.implementations.actions

import it.unibo.alchemist.model.interfaces.EuclideanEnvironment
import it.unibo.alchemist.model.interfaces.Node
import it.unibo.alchemist.model.interfaces.Position2D
import it.unibo.alchemist.model.interfaces.Reaction
import it.unibo.alchemist.model.interfaces.SteeringActionWithTarget
import it.unibo.alchemist.model.interfaces.geometry.GeometricTransformation
import it.unibo.alchemist.model.interfaces.geometry.Vector2D
import it.unibo.alchemist.model.interfaces.properties.PedestrianProperty

/**
 * [CognitiveAgentSeek] behavior in a bidimensional environment, delegated to [CognitiveAgentFollowScalarField]
 * (this means the node tries to overtake others on its path,
 * in general its movements are more sophisticated than [CognitiveAgentSeek]).
 */
open class CognitiveAgentSeek2D<T, P, A>(
    /**
     * The environment the node is into.
     */
    protected val environment: EuclideanEnvironment<T, P>,
    reaction: Reaction<T>,
    override val pedestrian: PedestrianProperty<T>,
    /**
     * The position the node wants to reach.
     */
    private val target: P
) : AbstractSteeringAction<T, P, A>(environment, reaction, pedestrian),
    SteeringActionWithTarget<T, P>
    where P : Position2D<P>, P : Vector2D<P>,
          A : GeometricTransformation<P> {

    constructor(
        environment: EuclideanEnvironment<T, P>,
        reaction: Reaction<T>,
        pedestrian: PedestrianProperty<T>,
        x: Number,
        y: Number,
    ) : this(environment, reaction, pedestrian, environment.makePosition(x, y))

    private val followScalarField = CognitiveAgentFollowScalarField(environment, reaction, pedestrian, target) {
        -it.distanceTo(target)
    }

    override fun target(): P = target

    override fun nextPosition(): P = followScalarField.nextPosition()

    override fun cloneAction(node: Node<T>, reaction: Reaction<T>): CognitiveAgentSeek2D<T, P, A> =
        CognitiveAgentSeek2D(environment, reaction, node.pedestrianProperty, target)
}
