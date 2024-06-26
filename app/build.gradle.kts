@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "ru.coincorn.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.coincorn.app"
        minSdk = 26
        targetSdk = 34
        versionCode = 5
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"http://94.158.218.130:8080/\"")
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:8080/\"")
            versionNameSuffix = "_debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "okhttp3/"
            excludes += "kotlin/"
            excludes += "org/"
            excludes += ".properties"
            excludes += ".bin"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.material3)
    implementation(libs.persian)
    implementation(libs.splash)

    api(libs.compose.foundation)
    api(libs.compose.foundation.layout)

    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)

    implementation(libs.compose.navigation)

    implementation(libs.glide)
    implementation(libs.lottie)

    implementation(libs.datastore)
    implementation(libs.collection.immutable)
    implementation(libs.serialization)

    implementation(libs.hilt)
    implementation(libs.firebase.messaging)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation)

    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.lifecycle)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}

