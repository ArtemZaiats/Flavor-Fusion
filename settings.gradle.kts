pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Flavor_Fusion"
include(":app")
include(":common:common-data")
include(":common:common-domain")
include(":common:common-ui")
include(":core:core-data")
include(":core:core-ui")
include(":features:drinks")
include(":features:meals")
include(":features:settings")
include(":features:auth")
include(":features:home")
include(":features:favorites")
