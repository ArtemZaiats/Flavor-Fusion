plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.library.compose)
    alias(libs.plugins.common.android.hilt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.flavorfusion.common_ui"
}

dependencies {
    api(projects.core.coreUi)
    implementation(projects.common.commonDomain)
    implementation(libs.youtube.player)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.lottie.compose)
    implementation(libs.coil.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.ext)
    androidTestImplementation(libs.androidx.espresso.core)
}
