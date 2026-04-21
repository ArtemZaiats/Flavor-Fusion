import com.android.build.api.dsl.ApplicationExtension
import com.flavorfusion.gradleplugins.configureKotlinAndroid
import com.flavorfusion.gradleplugins.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureDefaultConfig(libs)
            }
        }
    }
}

private fun ApplicationExtension.configureDefaultConfig(libs: VersionCatalog) {
    defaultConfig {
        applicationId = "com.flavorfusion.flavorfusion"
        targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
        versionCode = libs.findVersion("versionCode").get().requiredVersion.toInt()
        versionName = libs.findVersion("versionName").get().requiredVersion

        vectorDrawables {
            useSupportLibrary = true
        }
    }
}