package com.macrosystems.core.presentation.designsystem.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.macrosystems.core.presentation.designsystem.R

@Composable
fun RoundedImage(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    url: String
) {
    Box(
        modifier = Modifier
            .size(100.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .then(
                    if (isSelected) {
                        Modifier.border(4.dp, Color.Green, CircleShape)
                    } else {
                        Modifier
                    }
                )

                .shadow(8.dp, CircleShape)
                .clip(CircleShape)
            ,
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .fallback(R.drawable.avatar)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                clipToBounds = true
            )
        }

    }
}

@Preview
@Composable
fun RoundedImagePreview() {
    Column {
        Text("hello")
        RoundedImage(
            isSelected = true,
            url = "https://www.colorblends.com/wp-content/uploads/2020/01/1504_BestPurple_CGC2662sq-1024x1024.jpg"
        )
    }
}