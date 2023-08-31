pluginManagement {
    repositories {
        google()
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

rootProject.name = "news"
include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:model")
include(":core:database")
include(":core:network")
include(":core:data")
include(":feature:articles")
include(":feature:favourites")
include(":feature:profile")
include(":feature:details")