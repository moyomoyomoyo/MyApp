package com.example.myapplication.feed.viewmodel

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.feed.model.FeedPostWrapper
import com.example.myapplication.feed.model.FeedRepository
import kotlinx.coroutines.launch

enum class FeedSession {
    LIST,
    DETAIL
}

class FeedViewModel(
    private val feedRepository: FeedRepository
) : ViewModel() {
    // Stato navigazione
    var currentScreen by mutableStateOf(FeedSession.LIST)
        private set

    var feedPosts by mutableStateOf<List<FeedPostWrapper>>(emptyList())
        private set

    var selectedPost by mutableStateOf<FeedPostWrapper?>(null)
        private set

    // Stato caricamento
    var isLoading by mutableStateOf(false)
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    // Stato scroll
    var firstVisibleItemIndex by mutableStateOf(0)
        private set

    var firstVisibleItemScrollOffset by mutableStateOf(0)
        private set

    init {
        fetchNewPosts()
    }

    fun navigateTo(
        screen: FeedSession,
        post: FeedPostWrapper? = null
    ) {
        currentScreen = screen
        selectedPost = post
    }

    fun fetchNewPosts() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            val newPosts = feedRepository.getFeed()
            if (newPosts.isNotEmpty()) {
                feedPosts = feedPosts + newPosts
                Log.i("FeedViewModel", "Caricati ${newPosts.size} nuovi post")
            } else {
                Log.i("FeedViewModel", "Nessun nuovo post da caricare")
            }
            isLoading = false
        }
    }

    fun refreshList() {
        if (isRefreshing) return

        viewModelScope.launch {
            isRefreshing = true
            feedPosts = emptyList()
            feedRepository.reset()
            fetchNewPosts()
            isRefreshing = false
        }
    }

    fun saveScrollState(index: Int, offset: Int) {
        firstVisibleItemIndex = index
        firstVisibleItemScrollOffset = offset
    }
}