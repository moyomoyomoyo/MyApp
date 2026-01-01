package com.example.myapplication.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PostGridItem(post: Post, onPostClick: (Post) -> Unit) {
    Image(
        painter = painterResource(id = post.imageRes),
        contentDescription = null,
        modifier = Modifier
            .padding(2.dp)
            .aspectRatio(1f)
            .clickable { onPostClick(post) },
        contentScale = ContentScale.Crop
    )
}
