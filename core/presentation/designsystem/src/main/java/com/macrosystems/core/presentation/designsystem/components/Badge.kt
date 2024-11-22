package com.macrosystems.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color
) {
    Box(
        modifier = Modifier.background(
            color = Color.Gray.copy(alpha = 0.5f),
            shape = RoundedCornerShape(4.dp)
        )
    ) {
        Text(
            text.uppercase(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp),
            color = textColor
        )
    }
}