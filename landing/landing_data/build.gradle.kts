import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("com.google.gms.google-services")
    kotlin("plugin.serialization") version Kotlin.version
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"103.117.56.227:8080\"")
    }
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.landingDomain))

    "implementation"(Ktor.ktorClientCore)
    "implementation"(Ktor.ktorClientAndroid)
    "implementation"(Ktor.ktorSerialization)
    "implementation"(Ktor.ktorSerializationJson)
    "implementation"(Ktor.ktorClientLogging)
    "implementation"(Ktor.ktorClientAuth)
    "implementation"(Ktor.logBackClassic)

    "implementation"(platform(Firebase.firebaseBom))
    "implementation"(Firebase.firebaseAuth)
}