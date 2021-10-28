package mv.xpom10

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import mv.xpom10.generator.Generator
import mv.xpom10.generator.GeneratorFabric
import mv.xpom10.utils.FileUtils
import java.nio.file.Path
import java.util.function.Consumer
import javax.inject.Inject

open class CucumberTask
@Inject constructor(private val extension: CucumberPluginExtension) : DefaultTask() {

    companion object {
        const val NAME = "generateRunners"
        const val GROUP = "cucumber"
        const val DESCRIPTION = "Generate runners for cucumber features"
    }

    @TaskAction
    fun generate() {
        if (!extension.generate) {
            return
        }
        FileUtils.createDirectory(extension.outputDirectory)

        if (extension.cleanOutputDirectory) {
            FileUtils.cleanDirectory(extension.outputDirectory)
        }

        val generator: Generator = GeneratorFabric[extension.generateType]
        FileUtils.findFiles(extension.featuresDirectory, Regex(".+\\.feature"))
            .forEach(Consumer { feature: Path ->
                generator.generateRunner(extension, feature)
            })
    }

}