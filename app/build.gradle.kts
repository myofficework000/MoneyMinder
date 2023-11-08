plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.business.money_minder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.business.money_minder"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

//Additional for KSP set up
kotlin {
    jvmToolchain(17)
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("androidx.activity:activity-compose:1.8.0")
    // ktx activity with view model injection
    implementation("androidx.activity:activity-ktx:1.8.0")

    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("com.google.truth:truth:1.1.3")
    testImplementation ("app.cash.turbine:turbine:1.0.0")

    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("app.cash.turbine:turbine:1.0.0")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")

    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.0-alpha01")

    // Pager and Indicators - Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.23.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.23.1")
    implementation("com.google.accompanist:accompanist-flowlayout:0.23.1")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Compose Constraint Layout
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:2.8.1")

    // sentry sdk
    implementation("io.sentry:sentry-android:5.7.3")

    implementation("com.airbnb.android:lottie-compose:4.0.0")


    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("androidx.navigation:navigation-compose:2.5.0")

    // For Glide Image
    implementation("com.github.bumptech.glide:compose:1.0.0-alpha.5")

    //Room Dependency
    implementation("androidx.room:room-ktx:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")

    //Dagger-hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

}
