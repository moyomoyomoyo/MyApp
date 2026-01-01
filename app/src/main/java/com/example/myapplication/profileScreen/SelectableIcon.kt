package com.example.myapplication.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.myapplication.post.PostGrid

@Composable
fun SelectableIcon(
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (selected) Color(0xFF7d0885).copy(alpha = 0.2f) else Color.Transparent
    val tintColor = if (selected) Color(0xFF7d0885) else Color.Gray

    Box(
        modifier = Modifier
            .padding(4.dp)
//            .background(bgColor, RoundedCornerShape(8.dp))
            .clickable {
                if(icon == Icons.Outlined.List){
                } else {

                }
                onClick()
            }
            .padding(10.dp),
//        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tintColor,
            modifier = Modifier.size(21.dp)
        )
    }
}
