package mv.xpom10.generator

import mv.xpom10.CucumberPluginExtension
import mv.xpom10.utils.FileUtils.Companion.writeJavaFile
import mv.xpom10.utils.RunnerSource
import java.nio.file.Path

class GeneratorByFeatures : Generator() {
    override fun generateRunner(extension: CucumberPluginExtension, featurePath: Path) {
        val classSource = RunnerSource.getClassSource(featurePath, extension)
        writeJavaFile(extension.outputDirectory.toPath(), classSource)
    }
}