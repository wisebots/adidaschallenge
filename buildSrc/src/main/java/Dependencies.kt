object Modules {
    val app = ":app"
    val core = ":commons:core"
}

object Features {
    val fitgoals = ":features:fitgoals"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0.0-snapshot"
}

object Versions {

    // Android
    val gradle = "4.0.0"
    val compileSdk = 29
    val minSdk = 21
    val targetSdk = 29
    val buildTools = "29.0.3"

    // Injection
    val koin = "2.1.5"
    val koinTest = "1.0.2"

    // Kotlin
    val kotlin = "1.3.72"
    val coroutines = "1.2.1"

    // Architecture components
    val lifecycle = "2.1.0"
    val architecture = "2.2.0-rc03"

    // Support libraries
    val appcompat = "1.2.0-alpha01"
    val design = "1.2.0-alpha03"
    val supportLayout = "1.1.3"
    val constraintLayout = "2.0.0-alpha3"
    val cardview = "1.0.0"
    val recyclerview = "1.2.0-alpha01"
    val coordinatorLayout = "1.1.0"
    val drawerLayout = "1.0.0"
    val swipeRefresh = "1.0.0"

    // Reactive
    val rxjava = "2.2.16"
    val rxkotlin = "2.4.0"

    // Navigation libraries
    val navigation = "2.3.0-alpha06"
    val navPaging = "2.1.0"

    // Database libraries
    val room = "2.1.0"

    // Network libraries
    val retrofit = "2.7.0"
    val okhttp3 = "4.7.2"
    val retrofitCoroutines = "0.9.2"

    // Third Libraries
    val timeLib = "1.0.3"
    val glide = "4.11.0"
}

object KoinLibraries {
    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}"
    val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
}

object ArchitectureLibraries {
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.architecture}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.architecture}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val coordinatorlayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinatorLayout}"
    val drawerLayout = "androidx.drawerlayout:drawerlayout:${Versions.drawerLayout}"
    val swipeRefresh =  "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
}

object NavigationLibraries {
    val navigationRuntime = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
    val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    val navigationUI = "androidx.navigation:navigation-ui:${Versions.navigation}"
    val navigationktxFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationktxUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val navigationPaging = "androidx.paging:paging-runtime-ktx:${Versions.navPaging}"
    val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object ReactiveLibraries {
    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"
}

object DatabaseLibraries {
    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object NetworkLibraries {
    val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    val retrofitCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"

}

object Libraries {
    val timeLib = "com.github.kizitonwose:time:${Versions.timeLib}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object TestLibraries {
    val koinTesting = "org.koin:koin-test:${Versions.koinTest}"
}