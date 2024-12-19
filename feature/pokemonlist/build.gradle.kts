import com.sd.bforbanktest.build_logic.convention.implementation

plugins {
    alias(libs.plugins.bforbanktest.android.feature)
    alias(libs.plugins.bforbanktest.android.library.compose)
}

android {
    namespace = "com.sd.bforbanktest.feature.pokemonlist"

}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.common.gateway)
    implementation(projects.core.ui)

    testImplementation(testFixtures(projects.core.common.gateway))
}