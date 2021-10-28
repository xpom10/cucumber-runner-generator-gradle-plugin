package mv.xpom10.utils


import org.jboss.forge.roaster.Roaster
import org.jboss.forge.roaster.model.source.JavaClassSource
import mv.xpom10.CucumberPluginExtension
import java.nio.file.Path

object RunnerSource {
    fun getClassSource(feature: Path?, extension: CucumberPluginExtension): JavaClassSource {
        return with(Roaster.create(JavaClassSource::class.java)) {
            setName(
                extension.pattern + "_" + feature?.fileName.toString().replace("\\.feature".toRegex(), "")
            )
            setSuperType(extension.extendsClass)
            setPackage(extension.outputDirectory.toString().replace(".+java.", ""))
            addAnnotation(extension.platform?.cucumberOptions)
                .setStringValue("features", feature.toString())
                .setStringArrayValue("glue", extension.glue.toTypedArray())
                .setStringArrayValue("plugin", extension.plugins.toTypedArray())
                .setLiteralValue("strict", java.lang.String.valueOf(extension.strict))
                .setLiteralValue("monochrome", java.lang.String.valueOf(extension.strict))
            setPublic()
        }
    }

    fun getClassSourceForScenario(
        feature: Path,
        extension: CucumberPluginExtension,
        scenarioName: Array<String?>?,
        id: String
    ): JavaClassSource {
        return with(Roaster.create(JavaClassSource::class.java)) {
            setName(
                extension.pattern + "_" + feature.fileName.toString()
                    .replace("\\.feature".toRegex(), "_$id")
            )
            setSuperType(extension.extendsClass)
            setPackage(extension.runnerPackage)
            addAnnotation(extension.platform?.cucumberOptions)
                .setStringValue("features", feature.toString())
                .setStringArrayValue("name", scenarioName)
                .setStringArrayValue("glue", extension.glue.toTypedArray())
                .setStringArrayValue("plugin", extension.plugins.toTypedArray())
                .setLiteralValue("strict", java.lang.String.valueOf(extension.strict))
                .setLiteralValue("monochrome", java.lang.String.valueOf(extension.monochrome))
            setPublic()
        }
    }

}