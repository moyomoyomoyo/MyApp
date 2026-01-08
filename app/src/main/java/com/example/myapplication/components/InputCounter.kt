package com.example.myapplication.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun InputCounter(
    currentLength: Int,
    maxLength: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$currentLength/$maxLength",
        style = MaterialTheme.typography.bodySmall,
        color = if (currentLength > maxLength) Color.Red else Color.Gray,
        modifier = modifier
    )
}
