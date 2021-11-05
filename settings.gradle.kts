rootProject.name = "plugin"
include("cucumber-runner-generator")
include("sample-junit")
include("sample-testng")

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
