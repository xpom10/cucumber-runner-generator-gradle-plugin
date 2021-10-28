package mv.xpom10.generator

import mv.xpom10.generator.GenerateTypes
import mv.xpom10.generator.GeneratorByFeatures
import mv.xpom10.generator.GeneratorByScenarios
import java.lang.RuntimeException

object GeneratorFabric {
    operator fun get(type: GenerateTypes?): Generator {
        return when (type) {
            GenerateTypes.FEATURE -> GeneratorByFeatures()
            GenerateTypes.SCENARIO -> GeneratorByScenarios()
            else -> throw RuntimeException("Incorrect generate type")
        }
    }
}