plugins {
    alias(libs.plugins.common.android.feature)
    alias(libs.plugins.common.android.library.compose)
}

android {
    namespace = "com.flavorfusion.feature_favorites"
}

dependencies {
    implementation(libs.coil.compose)
}
