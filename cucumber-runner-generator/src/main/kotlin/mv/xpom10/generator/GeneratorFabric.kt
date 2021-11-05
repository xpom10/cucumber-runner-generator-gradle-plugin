package mv.xpom10.generator

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