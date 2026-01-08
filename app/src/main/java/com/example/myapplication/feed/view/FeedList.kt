package com.example.myapplication.feed.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.feed.model.FeedPostWrapper
import com.example.myapplication.feed.viewmodel.FeedViewModel

@Composable
fun FeedList(
    viewModel: FeedViewModel,
    onPostClick: (FeedPostWrapper) -> Unit
) {
    val lazyColState = rememberLazyListState(
        initialFirstVisibleItemIndex = viewModel.firstVisibleItemIndex,
        initialFirstVisibleItemScrollOffset = viewModel.firstVisibleItemScrollOffset
    )

    val currentIndex = remember { derivedStateOf { lazyColState.firstVisibleItemIndex } }
    val currentOffset = remember { derivedStateOf { lazyColState.firstVisibleItemScrollOffset } }
    val lastVisibleIndex = remember {
        derivedStateOf { lazyColState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
    }

    // Salva posizione scroll
    LaunchedEffect(currentIndex.value, currentOffset.value) {
        viewModel.saveScrollState(
            lazyColState.firstVisibleItemIndex,
            lazyColState.firstVisibleItemScrollOffset
        )
    }

    // Infinite scroll
    LaunchedEffect(lastVisibleIndex.value) {
        val lastVisible = lazyColState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        val total = lazyColState.layoutInfo.totalItemsCount
        val threshold = total - 3

        if (lastVisible != null && lastVisible >= threshold) {
            viewModel.fetchNewPosts()
        }
    }

    LazyColumn(
        state = lazyColState,
        modifier = Modifier.padding(horizontal = 5.dp)
    ) {
        Log.i("ViewFeedList", "Rendo ${viewModel.feedPosts.size} post")
        items(
            items = viewModel.feedPosts,
            key = { it.post.id }
        ) { postWrapper ->
            FeedPostItem(
                postWrapper = postWrapper,
                onClick = { onPostClick(postWrapper) }
            )
        }

        // Indicatore caricamento
        if (viewModel.isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
