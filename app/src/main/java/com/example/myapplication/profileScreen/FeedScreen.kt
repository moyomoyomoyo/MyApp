package com.example.myapplication.profileScreen

import androidx.compose.runtime.Composable
import com.example.myapplication.R

@Composable
fun FeedScreen() {
    val posts = listOf(
        Post(R.drawable.pf, "Tramonto al mare 🌅", "Spiaggia"),
        Post(R.drawable.pf, "Selfie in montagna 🏔️"),
        Post(R.drawable.pf, "Caffè del mattino ☕", "Bar centrale"),
        Post(R.drawable.pf, "Allenamento intenso 💪")
    )

    PostList(posts)
}
