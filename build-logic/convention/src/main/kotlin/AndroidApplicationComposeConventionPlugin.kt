import com.android.build.api.dsl.ApplicationExtension
import com.flavorfusion.gradleplugins.configureAndroidCompose
import com.flavorfusion.gradleplugins.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import kotlin.text.get

class AndroidApplicationComposeConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(project.pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)
                dependencies {
                    add("implementation", libs.findLibrary("activity.compose").get())
                }
            }
        }
    }
}