package com.macrosystems.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
) {
    commonExtension.run {
        buildFeatures {
            buildConfig = true
        }
        val githubApiKey = gradleLocalProperties(rootDir, rootProject.providers).getProperty("API_KEY")
        when(extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apikey = githubApiKey)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, apikey = githubApiKey)
                        }
                    }
                }
            }
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apikey = githubApiKey)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, apikey = githubApiKey)
                        }
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(apikey: String) {
    buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
    buildConfigField("String", "API_KEY", "\"$apikey\"")
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    apikey: String
) {
    buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
    buildConfigField("String", "API_KEY", "\"$apikey\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}