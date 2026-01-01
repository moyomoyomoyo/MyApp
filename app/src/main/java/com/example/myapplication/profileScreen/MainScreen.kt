package com.example.myapplication.profileScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.createPost.CreatePostScreen
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen

@Composable
fun MainScreen(nav: NavigationViewModel, modifier: Modifier) {

    when(nav.currentScreen){
        Screen.FEED -> FeedScreen(nav)
        Screen.PROFILE -> ProfileScreen(nav, modifier)
        Screen.CREATE_POST -> CreatePostScreen()
        Screen.PROFILE_SETTINGS -> ProfileSettings(nav)
    }
}