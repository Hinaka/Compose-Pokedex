plugins {
    id("pokedex.android.library")
    id("pokedex.android.library.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.ui.tooling.preview)
}