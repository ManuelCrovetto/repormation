plugins {
    alias(libs.plugins.repormation.android.library.compose)
}

android {
    namespace = "com.macrosystems.core.presentation.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.coil.compose)
    api(libs.androidx.material3)
}