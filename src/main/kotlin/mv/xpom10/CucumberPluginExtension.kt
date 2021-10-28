package mv.xpom10

import mv.xpom10.generator.GenerateTypes
import java.io.File

open class CucumberPluginExtension {

    companion object {
        const val NAME = "generator"
    }

    var outputDirectory: File = File("src/test/java")
    var featuresDirectory: File = File("src/test/resources")
    var pattern: String = "ScenarioRunner"
    var runnerPackage: String? = null
    var oneForkedFeatures: List<String> = listOf()
    var glue: List<String> = listOf()
    var plugins: List<String> = listOf()
    var tags: List<String> = listOf()
    var tests: List<String> = listOf()
    var strict: Boolean = true
    var monochrome: Boolean = false
    var cleanOutputDirectory: Boolean = false
    var extendsClass: String? = null
    var generateType: GenerateTypes = GenerateTypes.SCENARIO
    var generate: Boolean = true
    var platform: Platform? = null


    enum class Platform(val cucumberOptions: String) {
        junit4("io.cucumber.junit.CucumberOptions"),
        testng("io.cucumber.testng.CucumberOptions")
    }

}