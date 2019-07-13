package it.unibo.alchemist.behaviours

import it.unibo.alchemist.agents.cognitive.CognitivePedestrian
import it.unibo.alchemist.model.implementations.reactions.AbstractReaction
import it.unibo.alchemist.model.interfaces.TimeDistribution
import it.unibo.alchemist.model.interfaces.Time
import it.unibo.alchemist.model.interfaces.Node
import it.unibo.alchemist.model.interfaces.Environment

class CognitiveBehaviour<T>(
    private val pedestrian: CognitivePedestrian<T>,
    timeDistribution: TimeDistribution<T>
) : AbstractReaction<T>(pedestrian, timeDistribution) {

    override fun cloneOnNewNode(n: Node<T>?, currentTime: Time?) = TODO()

    override fun getRate() = timeDistribution.rate

    override fun updateInternalStatus(curTime: Time?, executed: Boolean, env: Environment<T, *>?) =
        pedestrian.cognitiveCharacteristics().forEach { it.update(rate) }
}