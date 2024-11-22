plugins {
    alias(libs.plugins.repormation.android.feature.ui)
}

android {
    namespace = "com.macrosystems.list.presentation"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.list.domain)
}