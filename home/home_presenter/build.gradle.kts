apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.homeDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(Accompanist.viewPager)
    "implementation"(Accompanist.viewPagerIndicator)

    "implementation"(AndroidX.paging)
    "implementation"(AndroidX.composePaging)
}