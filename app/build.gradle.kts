import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

fun getProperty(fileName: String, prop: String): Any? {
    val propsFile = rootProject.file(fileName)
    if (propsFile.exists()) {
        val props = Properties()
        props.load(FileInputStream(propsFile))
        return props[prop]
    }
    return null
}

android {
    compileSdkVersion(Versions.compileSdk)

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        applicationId = ApplicationID.default
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Versions.appVersionCode
        versionName = Versions.appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", "\"${getProperty("local.properties", "API_KEY")}\"")
    }
    buildTypes {
        getByName("release") {
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
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions("default")

    productFlavors {
        create("stable") {
            applicationId = ApplicationID.default
        }
        create("develop") {
            applicationId = ApplicationID.develop
            versionNameSuffix = "d"
        }
    }
}

kapt {
    useBuildCache = true
    correctErrorTypes = true
}

dependencies {
    arrayOf(
        Dependencies.kotlin,
        Dependencies.material,
        Dependencies.androidxCore,
        Dependencies.appcompat,
        Dependencies.moshi.core,
        Dependencies.constraint,
        Dependencies.recyclerView,
        Dependencies.retrofit.core,
        Dependencies.retrofit.moshi,
        Dependencies.timber,
        Dependencies.dagger.core,
        Dependencies.room.core,
        Dependencies.room.ktx,
        Dependencies.viewModel,
        Dependencies.epoxy.core,
        Dependencies.leakCanary,
        Dependencies.chucker.core,
        Dependencies.activity,
        Dependencies.fragment,
        Dependencies.coroutines.core,
        Dependencies.coroutines.android,
        Dependencies.glide.core
    ).forEach { dependency ->
        implementation(dependency)
    }

    arrayOf(
        Dependencies.leakSentry,
        Dependencies.chucker.noop
    ).forEach { dependency ->
        releaseImplementation(dependency)
    }

    arrayOf(
        Dependencies.moshi.codegen,
        Dependencies.dagger.compiler,
        Dependencies.room.compiler,
        Dependencies.glide.compiler,
        Dependencies.epoxy.compiler
    ).forEach { dependency ->
        kapt(dependency)
    }

    arrayOf(
        Dependencies.testRunner,
        Dependencies.JUnit.api,
        Dependencies.Mockk.android,
        Dependencies.JUnit.Android.core
    ).forEach { dependency ->
        androidTestImplementation(dependency)
    }
    arrayOf(
        Dependencies.Mockk.core,
        Dependencies.room.test,
        Dependencies.JUnit.api
    ).forEach { dependency ->
        testImplementation(dependency)
    }

    testRuntimeOnly(Dependencies.JUnit.engine)

    androidTestRuntimeOnly(Dependencies.JUnit.Android.runner)
    androidTestRuntimeOnly(Dependencies.JUnit.engine)
}
