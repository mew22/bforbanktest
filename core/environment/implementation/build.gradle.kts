plugins {
    alias(libs.plugins.bforbanktest.android.library)
    alias(libs.plugins.bforbanktest.android.hilt)
}

android {
    namespace = "com.sd.bforbanktest.core.environment.implementation"
}

dependencies {
    implementation(projects.core.environment.gateway)
}
