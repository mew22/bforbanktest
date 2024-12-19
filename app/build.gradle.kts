import com.sd.bforbanktest.build_logic.convention.implementation
import com.sd.bforbanktest.build_logic.convention.testRuntimeOnly

plugins {
    alias(libs.plugins.bforbanktest.android.application)
    alias(libs.plugins.bforbanktest.android.application.compose)
    alias(libs.plugins.bforbanktest.android.hilt)
}

android {
    namespace = "com.sd.bforbanktest"

    defaultConfig {
        applicationId = "com.sd.bforbanktest"
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    implementation(projects.core.network)
    implementation(projects.core.common.gateway)
    implementation(projects.core.common.implementation)
    implementation(projects.core.environment.gateway)
    implementation(projects.core.environment.implementation)
    implementation(projects.core.ui)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.navigation.compose)
}