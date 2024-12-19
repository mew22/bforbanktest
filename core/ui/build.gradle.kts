plugins {
    alias(libs.plugins.bforbanktest.android.library)
    alias(libs.plugins.bforbanktest.android.library.compose)
}

android {
    namespace = "com.sd.bforbanktest.core.ui"
}

dependencies {
    implementation(projects.core.common.gateway)
    implementation(libs.androidx.lifecycle.viewModelCompose)
}
