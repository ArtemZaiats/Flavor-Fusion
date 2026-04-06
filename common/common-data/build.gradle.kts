import java.util.Properties

plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
}

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) load(file.inputStream())
}

fun getKey(key: String): String =
    localProperties[key] as String?
        ?: System.getenv(key)
        ?: error("Missing property: $key")

android {
    namespace = "com.flavorfusion.common_data"

    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://www.thecocktaildb.com/api/json/v1/1/\""
            )
            buildConfigField(
                type = "String",
                name = "SUPABASE_URL",
                value = "\"${getKey("SUPABASE_URL")}\""
            )
            buildConfigField(
                type = "String",
                name = "SUPABASE_ANON_KEY",
                value = "\"${getKey("SUPABASE_ANON_KEY")}\""
            )
        }
        release {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://www.thecocktaildb.com/api/json/v1/1/\""
            )
            buildConfigField(
                type = "String",
                name = "SUPABASE_URL",
                value = "\"${getKey("SUPABASE_URL")}\""
            )
            buildConfigField(
                type = "String",
                name = "SUPABASE_ANON_KEY",
                value = "\"${getKey("SUPABASE_ANON_KEY")}\""
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

    implementation(platform(libs.supabase.bom))
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.utils)
    implementation(libs.supabase.postgrest)
    implementation(libs.supabase.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.ext)
    androidTestImplementation(libs.androidx.espresso.core)
}