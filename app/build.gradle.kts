plugins {
    id("pokedex.android.application")
    id("pokedex.android.application.compose")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("pokedex.spotless")
}

android {
    defaultConfig {
        applicationId = "dev.hinaka.pokedex"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = ".debug"
        }

        val release by getting {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":core:designsystem"))

    implementation(project(":feature:ability"))
    implementation(project(":feature:item"))
    implementation(project(":feature:location"))
    implementation(project(":feature:move"))
    implementation(project(":feature:nature"))
    implementation(project(":feature:type"))
    implementation(project(":feature:pokemon"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}