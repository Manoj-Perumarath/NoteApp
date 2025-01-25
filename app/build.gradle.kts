plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.manoj.noteapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.manoj.noteapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    hilt {
        enableAggregatingTask = true
    }
    buildFeatures {
        compose = true
    }
}
dependencies {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.material3)
    implementation(Dependencies.materialIconsExtended)
    implementation(Dependencies.constraintlayout)
    implementation(Dependencies.lifecycleLivedataKtx)
    implementation(Dependencies.lifecycleViewmodelKtx)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUiKtx)
    implementation(Dependencies.legacySupportV4)
    implementation(Dependencies.foundationAndroid)
    implementation(Dependencies.material3Android)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidxJunit)
    androidTestImplementation(Dependencies.espressoCore)
    implementation(Dependencies.uiToolingPreviewAndroid)
    implementation(Dependencies.lifecycleRuntimeKtx)

    //Compose
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.viewmodelCompose)

    //Hilt
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
    implementation(Dependencies.hiltNavigationCompose)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)

    implementation(Dependencies.okHttp)
    implementation(Dependencies.loggingInterceptor)

    //Room
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    annotationProcessor(Dependencies.roomCommon)
    kapt(Dependencies.roomCompiler)
}
kapt {
    correctErrorTypes = true
}