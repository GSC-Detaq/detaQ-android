plugins {
    id("dagger.hilt.android.plugin")
}

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.reminderDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(Accompanist.viewPager)
    "implementation"(Accompanist.viewPagerIndicator)
}