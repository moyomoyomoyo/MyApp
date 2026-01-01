package com.example.myapplication.profileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ProfileImage(
    imageRes: Int,
    size: Dp = 120.dp,
    isFriend: Boolean = false
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(size)
            .then(
                if (isFriend) {
                    Modifier.border(
                        width = 3.dp,
                        color = Color(0xFF7d0885),
                        shape = CircleShape
                    )
                } else {
                    Modifier
                }
            )
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}
