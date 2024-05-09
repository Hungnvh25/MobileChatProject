plugins {
    alias(libs.plugins.androidApplication)
<<<<<<< HEAD
    id("com.google.gms.google-services")
=======
>>>>>>> origin/main
}

android {
    namespace = "com.example.mychat"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mychat"
        minSdk = 26
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
}

dependencies {

    implementation(libs.appcompat)
<<<<<<< HEAD
    implementation(libs.google.services)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.bom))
=======
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
>>>>>>> origin/main
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.countryCodePicker)
}