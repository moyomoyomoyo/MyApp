package com.example.myapplication.post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.navigation.NavigationViewModel
import java.time.LocalDateTime

data class Post(
    val imageRes: Int,
    val description: String,
    val location: String? = null,
    val createdAt: LocalDateTime
)

//data class Pos(
//    val id: Int,
//    val authorId: Int,
//    val createdAt: LocalDateTime,
//    val contentPicture: String,
//    val contentText: String,
//    val longitude: Double?,
//    val latitude: Double?
//)

@Composable
fun PostList(nav: NavigationViewModel, posts: List<Post>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(posts) { post ->
            PostItem(nav, post)
        }
    }
}



