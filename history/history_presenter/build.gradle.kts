apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.historyDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(Accompanist.viewPager)
}