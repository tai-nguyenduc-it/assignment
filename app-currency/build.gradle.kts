import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.crypto.currency"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.crypto.currency"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("Development") {
            dimension = "version"
            applicationIdSuffix = ".dev"
            manifestPlaceholders["app_name"] = "Currency-Development"
        }
        create("Production") {
            dimension = "version"
            manifestPlaceholders["app_name"] = "Currency"
        }
        create("Staging") {
            dimension = "version"
            applicationIdSuffix = ".staging"
            manifestPlaceholders["app_name"] = "Currency-Staging"
        }
    }
    applicationVariants.all {
        outputs.all {
            val output = this as ApkVariantOutputImpl
            output.outputFileName = "Currency-v$versionName-$versionCode-$flavorName.apk"
        }
    }

    buildFeatures.buildConfig = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    implementation(project(":currency-ui"))
    implementation(project(":currency-presentation"))
    implementation(project(":currency-domain"))
    implementation(project(":currency-data"))
    implementation(project(":currency-datasource"))
    implementation(project(":common"))
    implementation(project(":analytics"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}
