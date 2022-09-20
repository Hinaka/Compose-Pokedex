plugins {
    id("pokedex.android.library")
    id("pokedex.android.library.compose")
    id("pokedex.spotless")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:designsystem"))

    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.material)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
}