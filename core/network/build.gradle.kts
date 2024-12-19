import com.sd.bforbanktest.build_logic.convention.implementation
import com.sd.bforbanktest.build_logic.convention.libs
import com.sd.bforbanktest.build_logic.convention.testImplementation

plugins {
    alias(libs.plugins.bforbanktest.android.library)
    alias(libs.plugins.bforbanktest.android.hilt)
}

android {
    namespace = "com.sd.bforbanktest.core.network"
}

dependencies {
    implementation(projects.core.environment.gateway)
    implementation(projects.core.environment.gateway)
    implementation(libs.retrofit.core)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.moshi.converter)
    implementation(libs.moshi)
    testImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.compiler)
    kspTest(libs.moshi.codegen)
}
