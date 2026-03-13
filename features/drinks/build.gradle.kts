plugins {
    alias(libs.plugins.common.android.feature)
    alias(libs.plugins.common.android.library.compose)
}

android {
    namespace = "com.flavorfusion.feature_drinks"
}

dependencies {
    implementation(libs.coil.compose)
}