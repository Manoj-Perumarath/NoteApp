object Dependencies {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val androidxJunit by lazy { "androidx.test.ext:junit:${Versions.junitVersion}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCore}" }
    val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
    val composeBom by lazy { "androidx.compose:compose-bom:${Versions.composeBom}" }
    val composeUi by lazy { "androidx.compose.ui:ui" }
    val composeMaterial by lazy { "androidx.compose.material:material" }
    val composeUiGraphics by lazy { "androidx.compose.ui:ui-graphics" }
    val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling" }
    val composeUiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }
    val composeUiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest" }
    val composeUiTestJunit4 by lazy { "androidx.compose.ui:ui-test-junit4" }
    val material3 by lazy { "androidx.compose.material3:material3" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltCompiler}" }
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }
    val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}" }
    val moshi by lazy { "com.squareup.moshi:moshi:${Versions.moshi}" }
    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshi}" }
    val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}" }
    val okHttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okHttp}" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}" }
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}" }
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
    val coreSplashscreen by lazy { "androidx.core:core-splashscreen:${Versions.coreSplashscreen}" }
    val coilCompose by lazy { "io.coil-kt:coil-compose:${Versions.coilCompose}" }
    val appcompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val constraintlayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}" }
    val lifecycleLivedataKtx by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLivedataKtx}" }
    val lifecycleViewmodelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewmodelKtx}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }
    val navigationFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKtx}" }
    val navigationUiKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigationUiKtx}" }
    val legacySupportV4 by lazy { "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4}" }
    val foundationAndroid by lazy { "androidx.compose.foundation:foundation-android:${Versions.foundationAndroid}" }
    val material3Android by lazy { "androidx.compose.material3:material3-android:${Versions.material3Android}" }
    val uiToolingPreviewAndroid by lazy { "androidx.compose.ui:ui-tooling-preview-android:${Versions.uiToolingPreviewAndroid}" }
    val materialIconsExtended by lazy { "androidx.compose.material:material-icons-extended:${Versions.materialIconsExtended}" }
    val hiltLifecycleViewmodel by lazy { "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycleViewmodel}" }
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hiltAndroidCompiler}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}" }
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.roomCommon}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.roomCommon}" }
    val roomCommon by lazy { "androidx.room:room-common:${Versions.roomCommon}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.roomKtx}" }
    val viewmodelCompose by lazy {"androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelCompose}"}
}

object Plugins {
    val kotlinCompose by lazy { "org.jetbrains.kotlin.plugin.compose:${Versions.kotlin}"}
    val kotlinAndroid by lazy { "org.jetbrains.kotlin.android:${Versions.kotlin}"}
    val androidApp by lazy { "com.android.application:${Versions.agp}"}
}
object Modules {
    const val mylibrary = ":mylibrary"
}