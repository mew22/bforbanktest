import com.sd.bforbanktest.build_logic.convention.implementation
import com.sd.bforbanktest.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(libs.plugins.kotlin.serialization.get().pluginId)
                apply("bforbanktest.android.library")
                apply("bforbanktest.android.hilt")
                apply("bforbanktest.android.detekt")
            }

            dependencies {

                // Define common dependencies for feature modules
                implementation(libs.androidx.navigation.compose)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.androidx.hilt.navigation.compose)
                implementation(libs.androidx.hilt.navigation.compose)
                implementation(libs.androidx.lifecycle.runtimeCompose)
                implementation(libs.androidx.lifecycle.viewModelCompose)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.kotlinx.collections.immutable)

                implementation(libs.retrofit.core)
                implementation(libs.moshi)
                "ksp"(libs.moshi.codegen)
            }
        }
    }
}
