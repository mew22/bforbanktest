import com.sd.bforbanktest.build_logic.convention.implementation
import com.sd.bforbanktest.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.hilt.get().pluginId)
                apply(libs.plugins.ksp.get().pluginId)
            }

            dependencies {
                implementation(libs.hilt.android)
                "ksp"(libs.hilt.compiler)
            }
        }
    }
}
