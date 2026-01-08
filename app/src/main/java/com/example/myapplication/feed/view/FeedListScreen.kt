package com.example.myapplication.feed.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import com.example.myapplication.feed.model.FeedPostWrapper
import com.example.myapplication.feed.viewmodel.FeedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedListScreen(
    viewModel: FeedViewModel,
    onPostClick: (FeedPostWrapper) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = viewModel.isRefreshing,
        onRefresh = { viewModel.refreshList() }
    ) {
        FeedList(
            viewModel = viewModel,
            onPostClick = onPostClick
        )
    }
}
