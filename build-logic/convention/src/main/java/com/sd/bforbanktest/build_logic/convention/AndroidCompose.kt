package com.sd.bforbanktest.build_logic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
            buildConfig = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.versions.kotlin.get()
        }

        dependencies {
            val bom = libs.androidx.compose.bom
            implementation(platform(bom))
            androidTestImplementation(platform(bom))
            implementation(libs.androidx.compose.material3)
            debugImplementation(libs.androidx.compose.ui.tooling)
            implementation(libs.androidx.compose.ui.tooling.preview)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.okhttp)
            implementation(libs.androidx.navigation.compose)
        }
    }
}
