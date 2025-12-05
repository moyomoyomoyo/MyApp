package com.example.myapplication.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.R

@Composable
fun FeedScreen() {
    Column (
        modifier = Modifier
        .background(Color(0xFFe5d3e5))
    ) {
        val posts = listOf(
            Post(R.drawable.pf, "Post1", "Spiaggia"),
            Post(R.drawable.pf, "Post2"),
            Post(R.drawable.pf, "Post3"),
            Post(R.drawable.pf, "Post4")
        )

        PostList(posts)
    }

}
