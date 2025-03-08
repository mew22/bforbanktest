import com.android.build.api.dsl.ApplicationExtension
import com.sd.bforbanktest.build_logic.convention.configureAndroidCompose
import com.sd.bforbanktest.build_logic.convention.configureComposeCompiler
import com.sd.bforbanktest.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.android.application.get().pluginId)
                apply(libs.plugins.compose.compiler.get().pluginId)
                apply("bforbanktest.android.detekt")
            }
            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
            val composeCompilerExt = extensions.getByType<ComposeCompilerGradlePluginExtension>()
            configureComposeCompiler(composeCompilerExt)
        }
    }
}
