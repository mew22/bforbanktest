import com.sd.bforbanktest.build_logic.convention.libs

plugins {
    alias(libs.plugins.bforbanktest.android.library)
    alias(libs.plugins.bforbanktest.android.hilt)
}

android {
    namespace = "com.sd.bforbanktest.core.common.gateway"

    testFixtures {
        enable = true
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testFixturesImplementation(libs.turbine)
    testFixturesImplementation(libs.junit.jupiter.api)
    testFixturesImplementation(libs.mockk)
    testFixturesImplementation(libs.kotlinx.coroutines.test)
    testFixturesImplementation(libs.okhttp.mock.webserver)
}
