import com.android.tools.build.bundletool.model.utils.Versions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.weatherglobantapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weatherglobantapp"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions{
        kotlinCompilerExtensionVersion = "1.5.1"
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("com.google.android.gms:play-services-location:21.2.0")
    implementation("androidx.compose.material3:material3-android:1.2.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.5")
    val retrofit = "2.11.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    implementation("androidx.activity:activity-compose:1.8.2")
    implementation ("io.coil-kt:coil-compose:2.0.0")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    testImplementation("app.cash.turbine:turbine:0.11.0")


    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1-Beta")
    testImplementation ("io.mockk:mockk:1.13.10")
    testImplementation ("com.google.truth:truth:1.4.2")



}// Allow references to generated code
kapt {
    correctErrorTypes = true
}