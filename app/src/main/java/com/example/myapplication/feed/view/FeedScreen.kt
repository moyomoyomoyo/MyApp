package com.example.myapplication.feed.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.data.viewmodel.UserViewModel
import com.example.myapplication.feed.model.FeedRepository
import com.example.myapplication.feed.viewmodel.FeedSession
import com.example.myapplication.feed.viewmodel.FeedViewModel
import com.example.myapplication.navigation.Header
import com.example.myapplication.navigation.NavigationViewModel

@Composable
fun FeedScreen(
    posts: List<PostEntity?>,
    user: UserEntity?,
    nav: NavigationViewModel,
    uvm: UserViewModel
) {
    // Crea il FeedRepository (passa UserViewModel per risolvere authorId)
    val feedRepository = remember(posts) {
        FeedRepository(uvm.userDao)
    }

    val feedViewModel: FeedViewModel = viewModel {
        FeedViewModel(feedRepository)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFe5d3e5))
    ) {
        Header(nav)

        when (feedViewModel.currentScreen) {
            FeedSession.LIST -> FeedListScreen(
                viewModel = feedViewModel,
                onPostClick = { wrapper ->
                    feedViewModel.navigateTo(FeedSession.DETAIL, wrapper)
                }
            )

            FeedSession.DETAIL -> FeedDetailScreen(
                viewModel = feedViewModel,
                onBackClick = {
                    feedViewModel.navigateTo(FeedSession.LIST)
                }
            )
        }
    }
}
