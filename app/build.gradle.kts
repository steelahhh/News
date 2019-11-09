import com.android.build.gradle.internal.dsl.TestOptions
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
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

    signingConfigs {
        create("release") {
            storeFile = file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
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
    testOptions {
        unitTests(delegateClosureOf<TestOptions.UnitTestOptions> {
            isReturnDefaultValues = true
        })
    }
    lintOptions {
        error("VisibleForTests")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = true
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
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {
    arrayOf(
        Dependencies.kotlin,
        Dependencies.material,
        Dependencies.androidX.core,
        Dependencies.androidX.appcompat,
        Dependencies.moshi.core,
        Dependencies.androidX.constraint,
        Dependencies.androidX.recyclerView,
        Dependencies.retrofit.core,
        Dependencies.retrofit.moshi,
        Dependencies.timber,
        Dependencies.dagger.core,
        Dependencies.room.core,
        Dependencies.room.ktx,
        Dependencies.androidX.viewModel,
        Dependencies.androidX.liveData,
        Dependencies.androidX.navigationCore,
        Dependencies.androidX.navigationUi,
        Dependencies.epoxy.core,
        Dependencies.leakCanary,
        Dependencies.androidX.activity,
        Dependencies.androidX.fragment,
        Dependencies.coroutines.core,
        Dependencies.coroutines.android,
        Dependencies.threeTenAbp,
        Dependencies.glide.core
    ).forEach { dependency ->
        implementation(dependency)
    }

    debugImplementation(Dependencies.chucker.core)

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
        Dependencies.junitExt,
        Dependencies.rules,
        Dependencies.Mockk.android
    ).forEach { dependency ->
        androidTestImplementation(dependency)
    }
    arrayOf(
        Dependencies.junit,
        Dependencies.coroutines.test,
        Dependencies.Mockk.core,
        Dependencies.room.test,
        Dependencies.junitExt,
        Dependencies.androidX.testCore
    ).forEach { dependency ->
        testImplementation(dependency)
    }
}
