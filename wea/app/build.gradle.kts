
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

}

android {
    namespace = "com.artist.wea"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.artist.wea"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        manifestPlaceholders["NAVER_CLIENT_ID"] = "NAVER_CLIENT_ID"
        buildConfigField("String", "NAVER_CLIENT_ID", getApiKey("NAVER_CLIENT_ID"))

        manifestPlaceholders["TOSS_CLIENT_KEY"] = "TOSS_CLIENT_KEY"
        buildConfigField("String", "TOSS_CLIENT_KEY", getApiKey("TOSS_CLIENT_KEY"))

        manifestPlaceholders["TOSS_SECRET_KEY"] = "TOSS_SECRET_KEY"
        buildConfigField("String", "TOSS_SECRET_KEY", getApiKey("TOSS_SECRET_KEY"))

    }


    buildTypes {

        debug {
            buildConfigField("String", "NAVER_CLIENT_ID", getApiKey("NAVER_CLIENT_ID"))
            buildConfigField("String", "TOSS_CLIENT_KEY", getApiKey("TOSS_CLIENT_KEY"))
            buildConfigField("String", "TOSS_SECRET_KEY", getApiKey("TOSS_SECRET_KEY"))
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "NAVER_CLIENT_ID", getApiKey("NAVER_CLIENT_ID"))
            buildConfigField("String", "TOSS_CLIENT_KEY", getApiKey("TOSS_CLIENT_KEY"))
            buildConfigField("String", "TOSS_SECRET_KEY", getApiKey("TOSS_SECRET_KEY"))
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
        compose = true
        buildConfig = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlin {
        jvmToolchain(8)
    }
}

dependencies {

    //default implementation
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.material:material:1.9.0")
    implementation("com.google.android.gms:play-services-ads-lite:22.3.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")
    // naver map
    implementation("com.naver.maps:map-sdk:3.17.0")
    // glide compose
    implementation("com.github.skydoves:landscapist-glide:1.4.7")

    // for asynchronous communication
    // retrofit2 and okhttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.9.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    // Cookie Management
    // implementation("com.github.franmontiel:PersistentCookieJar:v1.0.1'")
    // firebase
//    implementation("com.google.firebase:firebase-core:9.6.1")
//    implementation("platform('com.google.firebase:firebase-bom:32.0.0')")
//    implementation("com.google.firebase:firebase-analytics-ktx")
//    implementation("com.google.firebase:firebase-messaging:21.1.0")

    val toss_version = "0.1.11"
    val constraint_version = "2.1.4"

    // admobs
    implementation("com.google.android.gms:play-services-ads:22.3.0")
    // toss payments sdk
    implementation ("com.github.tosspayments:payment-sdk-android:$toss_version")

    // constraint layout
    implementation ("androidx.constraintlayout:constraintlayout:$constraint_version")

}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}
