plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.business.money_minder"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.business.money_minder"
        minSdk = 24
        targetSdk = 36
        versionCode = 3
        versionName = "2.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
        buildConfig = true
        viewBinding = true
        compose = true
    }
    packaging {
        resources {
            excludes += "META-INF/native-image/io.sentry/sentry/native-image.properties"
        }
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0-alpha01")
    implementation("androidx.appcompat:appcompat:1.6.1") // still latest stable
    implementation("com.google.android.material:material:1.10.0") // still latest stable
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // unchanged
    implementation("com.google.firebase:firebase-auth:20.0.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.compose.ui:ui-tooling-preview:1.8.2") // align with Compose UI
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.3")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.activity:activity-ktx:1.10.1")

    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("app.cash.turbine:turbine:1.0.0")

    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("app.cash.turbine:turbine:1.0.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")

    debugImplementation("androidx.compose.ui:ui-tooling:1.8.2") // matching preview

    implementation("androidx.navigation:navigation-compose:2.7.5")

    implementation("com.google.accompanist:accompanist-pager:0.23.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.23.1")
    implementation("com.google.accompanist:accompanist-flowlayout:0.32.0")

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-beta01")

    implementation("androidx.work:work-runtime-ktx:2.8.1") // latest stable

    implementation("io.sentry:sentry-android:5.7.3")

    implementation("com.airbnb.android:lottie-compose:6.0.1")

    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    implementation("com.github.bumptech.glide:compose:1.0.0-alpha.5")

    implementation("androidx.room:room-ktx:2.8.0-rc02")
    ksp("androidx.room:room-compiler:2.8.0-rc02")

    implementation("com.google.dagger:hilt-android:2.57.1")
    ksp("com.google.dagger:hilt-android-compiler:2.57.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
}
