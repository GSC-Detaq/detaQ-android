apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.sosDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(Google.mapsCompose)
    "implementation"(Google.playServicesMaps)
    "implementation"(Google.playServicesLocation)
}