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
        buildConfigField("String", "PREF_NAME", "\"DETAQ_PREFERENCE\"")
        buildConfigField("String", "SHOW_ON_BOARDING_KEY", "\"SHOW_ON_BOARDING\"")

        buildConfigField("String", "ENC_PREF_NAME", "\"DETAQ_ENC_PREFERENCE\"")
        buildConfigField("String", "TOKEN_KEY", "\"TOKEN\"")
        buildConfigField("String", "FCM_TOKEN_KEY", "\"FCM_TOKEN\"")

        buildConfigField("String", "CONTACT_BASE_URL", "\"103.117.56.227:8080\"")
    }
}

dependencies {
    "implementation"(Ktor.ktorClientCore)
    "implementation"(Ktor.ktorClientAndroid)
    "implementation"(Ktor.ktorSerialization)
    "implementation"(Ktor.ktorSerializationJson)
    "implementation"(Ktor.ktorClientLogging)
    "implementation"(Ktor.ktorClientAuth)
    "implementation"(Ktor.logBackClassic)

    "implementation"(AndroidX.encPreference)
    "implementation"(platform(Firebase.firebaseBom))
    "implementation"(Firebase.firebaseRealtimeDatabase)
}