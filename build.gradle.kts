buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()
        maven("https://www.jitpack.io")
        maven("https://maven.fabric.io/public")
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.androidPlugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.arch}")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
        maven("https://www.jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
