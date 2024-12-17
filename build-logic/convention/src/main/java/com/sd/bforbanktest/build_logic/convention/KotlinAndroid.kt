package com.sd.bforbanktest.build_logic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 35

        defaultConfig {
            minSdk = 26
        }

        buildFeatures {
            buildConfig = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }

        flavorDimensions += "version"
        productFlavors {
            create("mock") {
                dimension = "version"
            }

            create("prod") {
                dimension = "version"
            }
        }

        lint {
            disable += "FlowOperatorInvokedInComposition"
        }
    }
}
