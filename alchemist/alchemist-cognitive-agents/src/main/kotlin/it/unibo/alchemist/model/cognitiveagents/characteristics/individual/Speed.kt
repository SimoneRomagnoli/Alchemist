package it.unibo.alchemist.model.cognitiveagents.characteristics.individual

import com.uchuhimo.konf.Config
import it.unibo.alchemist.model.cognitiveagents.characteristics.Characteristic
import it.unibo.alchemist.model.cognitiveagents.characteristics.PARAMETERS_FILE
import it.unibo.alchemist.model.implementations.utils.nextDouble
import org.apache.commons.math3.random.RandomGenerator

/**
 * The speed of an agent considering its age, gender and a random factor.
 *
 * @param age
 *          the age of the agent.
 * @param gender
 *          the gender of the agent.
 * @param rg
 *          the simulation {@link RandomGenerator}.
 */
class Speed(age: Age, gender: Gender, rg: RandomGenerator) : Characteristic {

    /**
     * The walking speed of the agent.
     */
    val walking = when {
        age == Age.CHILD && gender == Gender.MALE -> childMale
        age == Age.CHILD && gender == Gender.FEMALE -> childFemale
        age == Age.ADULT && gender == Gender.MALE -> adultMale
        age == Age.ADULT && gender == Gender.FEMALE -> adultFemale
        age == Age.ELDERLY && gender == Gender.MALE -> elderlyMale
        else -> elderlyFemale
    } + rg.nextDouble(0.0, variance)

    /**
     * The running speed of the agent.
     */
    val running = walking * 3

    companion object {
        private val config = Config { addSpec(SpeedSpec) }
                .from.toml.resource(PARAMETERS_FILE)

        val childMale = config[SpeedSpec.childMale]
        val adultMale = config[SpeedSpec.adultMale]
        val elderlyMale = config[SpeedSpec.elderlyMale]
        val childFemale = config[SpeedSpec.childFemale]
        val adultFemale = config[SpeedSpec.adultFemale]
        val elderlyFemale = config[SpeedSpec.elderlyFemale]
        val default = config[SpeedSpec.default]
        val variance = config[SpeedSpec.variance]
    }
}