package com.example.myapplication.profileScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.myapplication.createPost.CreatePostScreen
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen

@Composable
fun MainScreen(nav: NavigationViewModel, modifier: Modifier) {
    var userName by remember { mutableStateOf("") }

    when(nav.currentScreen){
        Screen.FEED -> FeedScreen()
        Screen.PROFILE -> ProfileScreen(modifier)
        Screen.CREATE_POST -> CreatePostScreen()
    }

}