import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id ("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packagingOptions {
        resources {
            excludes.add("META-INF/*")
        }
    }
}

dependencies {
    implementation(Compose.compiler)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.navigation)
    implementation(Compose.runtime)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)

    debugImplementation(Compose.debugUiToolingPreview)
    debugImplementation(Compose.debugUiTestManifest)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    implementation(project(Modules.core))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.landingPresentation))
    implementation(project(Modules.landingDomain))
    implementation(project(Modules.landingData))
    implementation(project(Modules.homePresentation))
    implementation(project(Modules.homeDomain))
    implementation(project(Modules.homeData))
    implementation(project(Modules.sosPresentation))
    implementation(project(Modules.sosDomain))
    implementation(project(Modules.sosData))
    implementation(project(Modules.reminderPresentation))
    implementation(project(Modules.reminderDomain))
    implementation(project(Modules.reminderData))
    implementation(project(Modules.historyPresentation))
    implementation(project(Modules.historyDomain))
    implementation(project(Modules.historyData))
    implementation(project(Modules.profilePresentation))
    implementation(project(Modules.profileDomain))
    implementation(project(Modules.profileData))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.encPreference)

    implementation(Coil.coilCompose)

    implementation(Google.material)
    implementation(Google.mapsCompose)
    implementation(Google.playServicesMaps)
    implementation(Google.playServicesLocation)

    implementation(Ktor.ktorClientCore)
    implementation(Ktor.ktorClientAndroid)
    implementation(Ktor.ktorSerialization)
    implementation(Ktor.ktorSerializationJson)
    implementation(Ktor.ktorClientLogging)
    implementation(Ktor.ktorClientAuth)
    implementation(Ktor.logBackClassic)

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    implementation(ExtLogger.timber)

    implementation(Coroutine.coroutinesAndroid)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.dexMaker)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)

    // firebase
    implementation(platform(Firebase.firebaseBom))
    implementation(Firebase.firebaseCloudMessaging)
}