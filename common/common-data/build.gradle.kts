plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.flavorfusion.common_data"

    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://www.thecocktaildb.com/api/json/v1/1/\""
            )
        }
        release {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://www.thecocktaildb.com/api/json/v1/1/\""
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.coreData)
    implementation(projects.common.commonDomain)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)

    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.androidx.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.adapters.result)
    implementation(libs.retrofit.converter.kotlin.serialization)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.chuck)
    implementation(libs.datastore.preferences)
    implementation(libs.datastore.preferences.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.ext)
    androidTestImplementation(libs.androidx.espresso.core)
}