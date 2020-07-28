private const val COROUTINES_VERSION = "1.3.7"
private const val DAGGER_VERSION = "2.28.1"
private const val KOTLIN_VERSION = "1.3.72"
private const val ANDROID_X_CORE_VERSION = "1.3.0"

object Dependencies {

    const val KOTLIN_JDK = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"

    const val DAGGER = "com.google.dagger:dagger:$DAGGER_VERSION"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:$DAGGER_VERSION"
    const val DAGGER_ANDROID_PROCESSOR =
        "com.google.dagger:dagger-android-processor:$DAGGER_VERSION"
    const val DAGGER_ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:$DAGGER_VERSION"

    const val ANDROID_X_CORE = "androidx.core:core-ktx:$ANDROID_X_CORE_VERSION"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.1.0"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:1.1.0"

    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:1.1.3"

    const val JUNIT = "junit:junit:4.12"
}