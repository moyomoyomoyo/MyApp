package com.example.myapplication.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.R
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.data.viewmodel.UserViewModel
import com.example.myapplication.navigation.Header
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.post.Post
import java.time.LocalDateTime
//
//@Composable
//fun FeedScreen(posts: List<PostEntity?>, user: UserEntity?, nav: NavigationViewModel, uvm: UserViewModel) {
//    Header(nav)
//    Column (
//        modifier = Modifier
//        .background(Color(0xFFe5d3e5))
//    ) {
//
//
//
////        PostList(posts, onPostClick = { post ->
////            nav.navigateToPostDetail(post.id)
////        })
//    }
//
//}
