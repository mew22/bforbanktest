import com.android.build.api.dsl.LibraryExtension
import com.sd.bforbanktest.build_logic.convention.configureAndroidCompose
import com.sd.bforbanktest.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager){
                apply(libs.plugins.android.library.get().pluginId)
                apply(libs.plugins.compose.compiler.get().pluginId)
                apply("bforbanktest.android.detekt")
            }
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}
