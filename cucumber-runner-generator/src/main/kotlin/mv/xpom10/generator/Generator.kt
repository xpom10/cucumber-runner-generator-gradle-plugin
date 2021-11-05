package mv.xpom10.generator

import mv.xpom10.CucumberPluginExtension
import java.nio.file.Path

abstract class Generator {

    abstract fun generateRunner(extension: CucumberPluginExtension, featurePath: Path)

}