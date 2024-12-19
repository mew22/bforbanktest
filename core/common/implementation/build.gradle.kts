plugins {
    alias(libs.plugins.bforbanktest.android.library)
    alias(libs.plugins.bforbanktest.android.hilt)
}

android {
    namespace = "com.sd.bforbanktest.core.common.implementation"
}

dependencies {
    implementation(projects.core.common.gateway)
    implementation(libs.kotlinx.coroutines.android)
}
