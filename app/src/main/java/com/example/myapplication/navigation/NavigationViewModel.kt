package com.example.myapplication.navigation


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel


class NavigationViewModel : ViewModel() {
    var currentScreen by mutableStateOf(Screen.FEED)
        private set

    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }

}