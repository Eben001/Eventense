buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies{
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:1.7.10")

    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false

}