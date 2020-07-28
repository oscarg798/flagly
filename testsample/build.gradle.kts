plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.0")

    defaultConfig {
        applicationId = "com.oscarg798.flagly.testsample"
        minSdkVersion(AndroidConstants.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConstants.TARGET_SDK_VERSION)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = AndroidConstants.TEST_RUNNER
    }

    buildTypes {
        getByName(BuildTypesConstants.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(Dependencies.KOTLIN_JDK)
    implementation(Dependencies.ANDROID_X_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.CONSTRAINT_LAYOUT)

    testImplementation(Dependencies.JUNIT)
    
    implementation(project(mapOf("path" to ":app")))
}