import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.jfrog.bintray")
}

apply {
    from("../install.gradle.kts")
}


android {
    compileSdkVersion(AndroidConstants.COMPILE_SDK_VERSION)
    buildToolsVersion(AndroidConstants.BUILD_TOOLS_VERSION)

    defaultConfig {
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

    viewBinding {
        isEnabled = true
    }
}

val properties: Properties = Properties()
properties.load(FileInputStream("local.properties"))

bintray {
    user = properties.getProperty(BintrayConstanst.BINTRAY_USER_KEY)
    key = properties.getProperty(BintrayConstanst.BINTRAY_PASSWORD_KEY)
    publish = true

    setPublications(LibraryConstants.PUBLICATION_NAME)

    pkg.apply {
        repo = BintrayConstanst.REPO_NAME
        name = LibraryConstants.ARTIFACT_GROUP
        userOrg = BintrayConstanst.USER_ORG
        githubRepo = BintrayConstanst.GITHUB_URL
        vcsUrl = BintrayConstanst.GITHUB_URL
        description = LibraryConstants.POM_DESCRIPTION
        setLabels("kotlin")
        setLicenses(BintrayConstanst.LICENSE)
        desc = LibraryConstants.POM_DESCRIPTION
        websiteUrl = BintrayConstanst.GITHUB_URL
        issueTrackerUrl = BintrayConstanst.GITHUB_URL
        githubReleaseNotesFile = BintrayConstanst.GITHUB_URL

        version.apply {
            name = LibraryConstants.VERSION
            desc = LibraryConstants.POM_DESCRIPTION
            vcsTag = "v${LibraryConstants.VERSION}"
        }
    }
}

dependencies {
    implementation(Dependencies.KOTLIN_JDK)
    implementation(Dependencies.ANDROID_X_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.RECYCLER_VIEW)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)

    implementation(Dependencies.DAGGER)
    implementation(Dependencies.DAGGER_ANDROID_SUPPORT)
    kapt(Dependencies.DAGGER_COMPILER)
    kapt(Dependencies.DAGGER_ANDROID_PROCESSOR)

    implementation(Dependencies.CONSTRAINT_LAYOUT)

    testImplementation(Dependencies.JUNIT)

}