package mv.xpom10.generator

import io.cucumber.messages.IdGenerator
import io.cucumber.messages.IdGenerator.Incrementing
import org.jboss.forge.roaster.model.source.JavaClassSource
import mv.xpom10.CucumberPluginExtension
import mv.xpom10.utils.FeatureUtils
import mv.xpom10.utils.FileUtils.Companion.writeJavaFile
import mv.xpom10.utils.RunnerSource
import java.nio.file.Path

class GeneratorByScenarios : Generator() {
    override fun generateRunner(extension: CucumberPluginExtension, featurePath: Path) {
        val idGenerator: IdGenerator = Incrementing()
        val scenarios = FeatureUtils.getScenarios(featurePath, extension.tags, extension.tests)
        val oneForkedFeatures = extension.oneForkedFeatures
        if (!scenarios.isEmpty()) {
            if (oneForkedFeatures.contains(featurePath.fileName.normalize().toString())
            ) {
                val classSource = RunnerSource.getClassSourceForScenario(
                    featurePath,
                    extension,
                    scenarios.toTypedArray(),
                    idGenerator.newId()
                )
                writeJavaFile(extension.outputDirectory.toPath(), classSource)
            } else {
                scenarios.stream()
                    .map { scenario: String ->
                        RunnerSource.getClassSourceForScenario(
                            featurePath,
                            extension,
                            arrayOf(scenario),
                            idGenerator.newId()
                        )
                    }
                    .forEach { classSource: JavaClassSource? ->
                        writeJavaFile(
                            extension.outputDirectory.toPath(), classSource!!
                        )
                    }
            }
        }
    }
}