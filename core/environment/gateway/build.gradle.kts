plugins {
    alias(libs.plugins.bforbanktest.android.library)
    alias(libs.plugins.bforbanktest.android.hilt)
}

android {
    namespace = "com.sd.bforbanktest.core.environment.gateway"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
