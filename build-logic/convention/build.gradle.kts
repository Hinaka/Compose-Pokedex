plugins {
  `kotlin-dsl`
}

group = "dev.hinaka.pokedex.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  compileOnly(libs.android.gradleplugin)
  compileOnly(libs.kotlin.gradleplugin)
  compileOnly(libs.spotless.gradleplugin)
}

gradlePlugin {
  plugins {
    register("androidLibrary") {
      id = "pokedex.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("spotless") {
      id = "pokedex.spotless"
      implementationClass = "SpotlessConventionPlugin"
    }
  }
}