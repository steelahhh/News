object ApplicationID {
    const val default = "dev.steelahh.news"
    const val develop = "dev.steelahhh.news.develop"
}

object Modules {
    const val app = ":app"
}

object Versions {
    const val minSdk = 21
    const val targetSdk = 29
    const val compileSdk = 29
    const val appVersionCode = 1000
    const val appVersionName = "1.0.0"

    const val kotlin = "1.3.50"
    const val androidPlugin = "3.6.0-beta01"

    const val leakCanary = "2.0-alpha-3"

    const val arch = "2.2.0-rc01"
    const val room = "2.2.1"

    const val dagger = "2.25.2"
    const val moshi = "1.9.1"
    const val retrofit = "2.6.2"
    const val timber = "4.7.1"
    const val chucker = "3.0.1"

    const val glide = "4.10.0"

    const val epoxy = "3.8.0"

    const val mockk = "1.9.3.kotlin12"
    const val junit = "5.3.1"
    const val testRunner = "1.1.1"
}

object Dependencies {
    val vers = Versions

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${vers.kotlin}"

    const val appcompat = "androidx.appcompat:appcompat:1.1.0"
    const val androidxCore = "androidx.core:core-ktx:1.1.0"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0-rc01"
    const val material = "com.google.android.material:material:1.2.0-alpha01"
    const val constraint = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val fragment = "androidx.fragment:fragment-ktx:1.1.0"
    const val activity = "androidx.activity:activity-ktx:1.0.0"

    val dagger = Dagger

    object Dagger {
        const val core = "com.google.dagger:dagger:${vers.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${vers.dagger}"
    }

    val glide = Glide

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${vers.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${vers.glide}"
    }

    val epoxy = Epoxy

    object Epoxy {
        const val core = "com.airbnb.android:epoxy:${vers.epoxy}"
        const val compiler = "com.airbnb.android:epoxy-processor:${vers.epoxy}"
    }

    val coroutines = Coroutines

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.2"
    }

    val moshi = Moshi

    object Moshi {
        const val core = "com.squareup.moshi:moshi:${vers.moshi}"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:${vers.moshi}"
    }

    val retrofit = Retrofit

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${vers.retrofit}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${vers.retrofit}"
    }

    val chucker = Chucker

    object Chucker {
        const val core = "com.github.ChuckerTeam.Chucker:library:${vers.chucker}"
        const val noop = "com.github.ChuckerTeam.Chucker:library-no-op:${vers.chucker}"
    }

    val room = Room

    object Room {
        const val core = "androidx.room:room-common:${vers.room}"
        const val ktx = "androidx.room:room-ktx:${vers.room}"
        const val compiler = "androidx.room:room-compiler:${vers.room}"
        const val test = "androidx.room:room-compiler:${vers.room}"
    }

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${vers.arch}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${vers.arch}"

    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.2.1"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    const val leakSentry = "com.squareup.leakcanary:leaksentry:${Versions.leakCanary}"

    const val timber = "com.jakewharton.timber:timber:${vers.timber}"

    const val testRunner = "androidx.test:runner:${Versions.testRunner}"

    object JUnit {
        const val api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"

        object Android {
            const val core = "de.mannodermaus.junit5:android-test-core:1.0.0"
            const val runner = "de.mannodermaus.junit5:android-test-runner:1.0.0"
        }
    }

    object Mockk {
        const val core = "io.mockk:mockk:${Versions.mockk}"
        const val android = "io.mockk:mockk-android:${Versions.mockk}"
    }
}
