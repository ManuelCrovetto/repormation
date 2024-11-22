plugins {
    alias(libs.plugins.repormation.android.feature.ui)
}

android {
    namespace = "com.macrosystems.detail.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.androidx.activity.compose)

    implementation(projects.core.domain)
    implementation(projects.detail.domain)
}

