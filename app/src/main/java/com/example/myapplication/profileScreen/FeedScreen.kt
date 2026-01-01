package com.example.myapplication.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.R
import com.example.myapplication.navigation.Header
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.post.Post
import com.example.myapplication.post.PostList
import java.time.LocalDateTime

@Composable
fun FeedScreen(nav: NavigationViewModel) {
    Header(nav)
    Column (
        modifier = Modifier
        .background(Color(0xFFe5d3e5))
    ) {
        val posts = listOf(
            Post(R.drawable.pf, "questa descrizione deve essere molto lunga capito giussto per capire quanto va sotto e se va bene la gfrandezza dei post e dei font", "Spiaggia", LocalDateTime.now()),
            Post(R.drawable.pf, "Post2", null, LocalDateTime.now()),
            Post(R.drawable.pf, "Post3", null, LocalDateTime.now()),
            Post(R.drawable.pf, "Post4", "casa mia", LocalDateTime.now())
        )

        PostList(nav, posts)
    }

}
