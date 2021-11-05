package mv.xpom10

import org.gradle.api.Plugin
import org.gradle.api.Project

open class CucumberPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(CucumberPluginExtension.NAME, CucumberPluginExtension::class.java)
        val task = project.tasks.register(CucumberTask.NAME, CucumberTask::class.java, extension)
        task.configure {
            it.group = CucumberTask.GROUP
            it.description = CucumberTask.DESCRIPTION
        }
    }
}