plugins {
    alias(libs.plugins.repormation.android.library.compose)
}

android {
    namespace = "com.macrosystems.core.presentation.ui"
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(projects.core.domain)
}