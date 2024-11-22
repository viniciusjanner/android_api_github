plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.viniciusjanner.apigithub"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.viniciusjanner.apigithub"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true

        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.coreSplashScreen)
    implementation(libs.androidx.fragmentKtx)
    implementation(libs.androidx.lifecycle.livedataKtx)
    implementation(libs.androidx.lifecycle.viewmodelKtx)
    implementation(libs.androidx.multidex)
    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // Paging
    implementation(libs.androidx.paging)
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)

    // Google
    implementation(libs.google.dagger.hilt.android)
    kapt(libs.google.dagger.hilt.compiler)
    implementation(libs.google.material)

    // Squareup
    implementation(libs.squareup.moshi)
    implementation(libs.squareup.moshi.kotlin)
    implementation(libs.squareup.okhttp3.loggingInterceptor)
    implementation(libs.squareup.okhttp3.okhttp)
    implementation(platform(libs.squareup.okhttp3.okhttp3))
    implementation(libs.squareup.retrofit2.adapterRxjava3)
    implementation(libs.squareup.retrofit2.retrofit)

    // RxJava
    implementation(libs.rxjava3.rxjava)
    implementation(libs.rxjava3.rxandroid)
}