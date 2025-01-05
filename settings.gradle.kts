pluginManagement {
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
    }
}

rootProject.name = "Crypto Assignment"
include(":app-currency")
include(":currency-ui")
include(":currency-presentation")
include(":currency-domain")
include(":currency-data")
include(":currency-datasource")
include(":architecture:architecture-ui")
include(":architecture:architecture-presentation")
include(":architecture:architecture-domain")
include(":architecture:architecture-data")
include(":architecture:architecture-datasource")
include(":analytics")
include(":widget")
include(":common")
