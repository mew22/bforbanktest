import com.android.build.api.dsl.ApplicationExtension
import com.sd.bforbanktest.build_logic.convention.configureKotlinAndroid
import com.sd.bforbanktest.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.android.application.get().pluginId)
                apply(libs.plugins.jetbrains.kotlin.android.get().pluginId)
                apply(libs.plugins.android.junit5.plugin.get().pluginId)
                apply("bforbanktest.android.detekt")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}
