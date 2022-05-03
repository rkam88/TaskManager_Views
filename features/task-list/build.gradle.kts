import com.langfordapps.plugins.Constants

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {

    compileSdk = Constants.compileSdk

    defaultConfig {
        minSdk = Constants.minSdk
        targetSdk = Constants.targetSdk

        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = Constants.jvmCompatibility
        targetCompatibility = Constants.jvmCompatibility
    }

    kotlinOptions {
        jvmTarget = Constants.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:compose"))
    implementation(project(":core:task-storage"))
    implementation(libs.bundles.compose)
    implementation(libs.coreKtx)
    implementation(libs.lifecycleViewmodelKtx)
    implementation(libs.fragmentKtx)
}