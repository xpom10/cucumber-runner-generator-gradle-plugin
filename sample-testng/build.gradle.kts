plugins {
    java
    id("mv.xpom10.cucumber-runner-generator") version "1.0.0"
}

group = "mv.xpom10"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
}

generator {
    outputDirectory = File("${project.projectDir}/src/test/java/mv/xpom10/runners")
    featuresDirectory = File("${project.projectDir}/src/test/resources/mv/xpom10/features")
    pattern = "ScenarioRunner"
    extendsClass = "mv.xpom10.base.BaseTest"
    runnerPackage = "mv.xpom10.runners"
    glue = listOf("mv.xpom10")
    plugins = listOf()
    cleanOutputDirectory = true
    generateType = mv.xpom10.generator.GenerateTypes.SCENARIO
    platform = mv.xpom10.CucumberPluginExtension.Platform.testng
}

tasks.test {
    dependsOn("generateRunners")
    useTestNG() {
        parallel = "classes"
        threadCount = 3
    }

    filter {
        includeTestsMatching("Scenario*")
    }
}

dependencies {
    implementation("io.cucumber:cucumber-core:6.11.0")
    implementation("io.cucumber:cucumber-java:6.11.0")
    implementation("io.cucumber:cucumber-testng:6.11.0")
}