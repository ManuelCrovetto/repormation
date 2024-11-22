package com.macrosystems.core.presentation.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Story(
    modifier: Modifier = Modifier,
    imageUrl: String,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale = if (isSelected) 1.0f else 0.8f
    Column(
        modifier = modifier
            .scale(scale)
            .padding(horizontal = 8.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundedImage(url = imageUrl, isSelected = isSelected)
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun StoryPreview() {
    Story(imageUrl = "", text = "Manuel Crovetto", isSelected = true) {

    }
}