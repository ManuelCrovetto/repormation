package com.macrosystems.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macrosystems.core.presentation.designsystem.RepormationTheme

@Composable
fun RepoCard(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    stars: Int,
    language: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults
            .cardElevation(
                defaultElevation = 6.dp
            ),
        modifier = Modifier
            .padding(10.dp)
            ,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()

        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(5f)
            ) {
                Text(name, fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(description, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Badge(
                    text = language,
                    textColor = provideLanguageColor(language),
                    modifier = Modifier.background(
                        color = Color.Gray.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(4.dp)
                    )
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Star, "star", tint = Color.Yellow)
                    Spacer(modifier.width(8.dp))
                    Text(stars.toString(), fontWeight = FontWeight.Black)
                }
            }
        }
    }
}

private fun provideLanguageColor(language: String): Color {
    return when(language) {
        "Kotlin" -> Color.Blue
        "Java" -> Color.Red
        else -> Color.White
    }
}

@Preview(showBackground = true)
@Composable
private fun RepoCardPreview() {
    RepormationTheme {
        RepoCard(
            name = "x",
            description = "Cloning X social network!",
            stars = 100,
            language = "Kotlin"
        ) {

        }
    }
}