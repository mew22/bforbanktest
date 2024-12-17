import com.android.build.gradle.LibraryExtension
import com.sd.bforbanktest.build_logic.convention.configureKotlinAndroid
import com.sd.bforbanktest.build_logic.convention.libs
import com.sd.bforbanktest.build_logic.convention.testImplementation
import com.sd.bforbanktest.build_logic.convention.testRuntimeOnly
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.android.library.get().pluginId)
                apply(libs.plugins.jetbrains.kotlin.android.get().pluginId)
                apply(libs.plugins.android.junit5.plugin.get().pluginId)
                apply("bforbanktest.android.detekt")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
            }

            dependencies {
                testImplementation(kotlin("test"))
                testImplementation(libs.junit.jupiter.api)
                testImplementation(libs.junit.vintage.engine)
                testRuntimeOnly(libs.junit.jupiter.engine)
                testImplementation(libs.mockk)
                testImplementation(libs.turbine)
                testImplementation(libs.kotlinx.coroutines.test)
                testImplementation(libs.okhttp.mock.webserver)
            }
        }
    }
}
