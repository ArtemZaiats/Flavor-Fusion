import com.flavorfusion.gradleplugins.getKey

plugins {
    alias(libs.plugins.common.android.library)
    alias(libs.plugins.common.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
}

val supabaseUrl = getKey("SUPABASE_URL")
val supabaseAnonKey = getKey("SUPABASE_ANON_KEY")

android {
    namespace = "com.flavorfusion.common_data"

    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "COCKTAILS_BASE_URL",
                value = "\"https://www.thecocktaildb.com/api/json/v1/1/\""
            )
            buildConfigField(
                type = "String",
                name = "MEALS_BASE_URL",
                value = "\"https://www.themealdb.com/api/json/v1/1/\""
            )
            buildConfigField(
                type = "String",
                name = "SUPABASE_URL",
                value = "\"$supabaseUrl\""
            )
            buildConfigField(
                type = "String",
                name = "SUPABASE_ANON_KEY",
                value = "\"$supabaseAnonKey\""
            )
        }
        release {
            buildConfigField(
                type = "String",
                name = "COCKTAILS_BASE_URL",
                value = "\"https://www.thecocktaildb.com/api/json/v1/1/\""
            )
            buildConfigField(
                type = "String",
                name = "MEALS_BASE_URL",
                value = "\"https://www.themealdb.com/api/json/v1/1/\""
            )
            buildConfigField(
                type = "String",
                name = "SUPABASE_URL",
                value = "\"$supabaseUrl\""
            )
            buildConfigField(
                type = "String",
                name = "SUPABASE_ANON_KEY",
                value = "\"$supabaseAnonKey\""
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

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(platform(libs.supabase.bom))
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.utils)
    implementation(libs.supabase.postgrest)
    implementation(libs.supabase.auth)
    implementation(libs.supabase.storage)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.ext)
    androidTestImplementation(libs.androidx.espresso.core)
}