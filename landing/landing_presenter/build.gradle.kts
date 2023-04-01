plugins {
    id("com.google.gms.google-services")
}

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.landingDomain))

    "implementation"(Coil.coilCompose)
    "implementation"(platform(Firebase.firebaseBom))
    "implementation"(Firebase.firebaseAuth)
}