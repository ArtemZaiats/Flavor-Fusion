import com.flavorfusion.gradleplugins.getKey

plugins {
    alias(libs.plugins.common.android.feature)
    alias(libs.plugins.common.android.library.compose)
}

val webGoogleClientId = getKey("WEB_GOOGLE_CLIENT_ID")

android {
    namespace = "com.flavorfusion.feature_auth"

    buildTypes {
        debug {
            buildConfigField("String", "WEB_GOOGLE_CLIENT_ID", "\"$webGoogleClientId\"")
        }
        release {
            buildConfigField("String", "WEB_GOOGLE_CLIENT_ID", "\"$webGoogleClientId\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.credentials)
    implementation(libs.credentials.play.services)
    implementation(libs.googleid)
}
