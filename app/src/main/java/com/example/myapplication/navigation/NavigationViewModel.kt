package com.example.myapplication.navigation


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel


class NavigationViewModel : ViewModel() {
    var currentScreen by mutableStateOf(Screen.FEED)
        private set

    var pastScreen by mutableStateOf(Screen.FEED)
        private set

    var selectedPostId by mutableStateOf<Int?>(null)
        private set

    fun navigateTo(screen: Screen) {
        pastScreen = currentScreen
        currentScreen = screen
    }

    fun navigateBack() {
        currentScreen = pastScreen
    }

    fun navigateToPostDetail(postId: Int) {
        selectedPostId = postId
        currentScreen = Screen.POST_DETAIL
    }
}