plugins {
    java
    `java-gradle-plugin`
    `maven-publish`
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
}

group = "mv.xpom10"
version = "1.0.0"

tasks.withType(JavaCompile::class.java) {
    options.encoding = "UTF-8"
    sourceCompatibility = "11"
    targetCompatibility = "11"
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
    kotlinOptions.jvmTarget = "11"
}

gradlePlugin {
    plugins {
        create("cucumber-runner-generator") {
            id = "mv.xpom10.cucumber-runner-generator"
            implementationClass = "mv.xpom10.CucumberPlugin"
        }
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    api(gradleApi())
    api("org.jboss.forge.roaster:roaster-jdt:2.23.2.Final")
    implementation("org.jboss.forge.roaster:roaster-api:2.23.2.Final")
    implementation("commons-io:commons-io:2.6")
    implementation("io.cucumber:gherkin:13.0.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
}