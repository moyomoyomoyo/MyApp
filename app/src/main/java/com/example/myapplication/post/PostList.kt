package com.example.myapplication.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.profileScreen.ViewMode
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

//@Composable
//fun PostList(nav: NavigationViewModel, posts: List<PostEntity?>, user: UserEntity?) {
@Composable
fun PostList(
    user: UserEntity,
    nav: NavigationViewModel,
    posts: List<PostEntity?>,
    viewMode: ViewMode,
    onViewModeChange: (ViewMode) -> Unit,
    onPostClick: (PostEntity) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Profilo info (senza Header che ora è fisso)
        item {
            ProfileInfoContent(
                user = user,
                nav = nav,
                viewMode = viewMode,
                onViewModeChange = onViewModeChange
            )
        }

//        val bitmap = imagePicture()

        // Post in formato lista
        items(posts.filterNotNull()) { post ->
            PostItem(
                post = post,
                onClick = { onPostClick(post) }
            )
        }
    }
}
//    LazyColumn(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        items(posts) { post ->
//            PostItem(nav, post, user)
//        }
//    }
//}



