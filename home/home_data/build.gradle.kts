import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    kotlin("plugin.serialization") version Kotlin.version
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    defaultConfig {
        buildConfigField("String", "base_url", "\"103.117.56.227:8080\"")
    }
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.homeDomain))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)

    "implementation"(Ktor.ktorClientCore)
    "implementation"(Ktor.ktorClientAndroid)
    "implementation"(Ktor.ktorSerialization)
    "implementation"(Ktor.ktorSerializationJson)
    "implementation"(Ktor.ktorClientLogging)
    "implementation"(Ktor.ktorClientAuth)
    "implementation"(Ktor.logBackClassic)

    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}