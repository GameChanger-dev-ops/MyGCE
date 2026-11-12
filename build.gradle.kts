buildscript {
    dependencies {
        // AGP 9 bundles Kotlin Gradle plugin 2.2.10; use the Kotlin version from the catalog instead
        // (it must match the Compose compiler plugin). DECISIONS/0002.
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
