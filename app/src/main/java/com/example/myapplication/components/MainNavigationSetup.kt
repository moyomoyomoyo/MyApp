package com.example.myapplication.components

import com.example.myapplication.feed.view.FeedScreen
import com.example.myapplication.map.MapboxMapScreen
import com.example.myapplication.profileScreen.CreatePostScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.myapplication.data.viewmodel.UserViewModel
import com.example.myapplication.map.MapboxMapScreen
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen
import com.example.myapplication.post.PostDetail
import com.example.myapplication.profileScreen.ProfileScreen
import com.example.myapplication.profileScreen.ProfileSettings

@Composable
fun MainNavigationSetup(
    navViewModel: NavigationViewModel,
    userViewModel: UserViewModel,
    currentUserId: Int // ID dell'utente loggato
) {
    // Carica l'utente corrente
    LaunchedEffect(currentUserId) {
        userViewModel.loadUser(currentUserId)
    }

    val currentUser by userViewModel.user.collectAsState()
    val allPosts by userViewModel.posts.collectAsState()

    // Gestisci la navigazione
    when (navViewModel.currentScreen) {
        Screen.FEED -> {
            if (currentUser != null) {
                FeedScreen(
                    posts = allPosts,
                    user = currentUser,
                    nav = navViewModel,
                    uvm = userViewModel
                )
            }
        }

        Screen.PROFILE -> {
            // Determina quale profilo mostrare
            val profileUserId = /*uvm.selectedUserId ?:*/ currentUserId

            // Carica i post dell'utente
            LaunchedEffect(profileUserId) {
                userViewModel.loadUser(profileUserId)
                userViewModel.getPostsByUserId(profileUserId)
            }

            val profileUser by userViewModel.user.collectAsState()
            val userPosts by userViewModel.postId.collectAsState()

            if (currentUser != null && profileUser != null) {
                ProfileScreen(
                    user = profileUser,
//                    currentUser = currentUser!!,
                    nav = navViewModel,
                    userPosts = userPosts,
                    uvm = userViewModel
                )
            }
        }

        Screen.PROFILE_SETTINGS -> {
            if (currentUser != null) {
                ProfileSettings(
                    user = currentUser,
                    nav = navViewModel,
                    userViewModel = userViewModel
                )
            }
        }

        Screen.POST_DETAIL -> {
            navViewModel.selectedPostId?.let { postId ->
                if (currentUser != null) {
                    PostDetail(
                        postId = postId,
                        nav = navViewModel,
                        uvm = userViewModel
                    )
                }
            }
        }

        Screen.CREATE_POST -> {
            CreatePostScreen(
                nav = navViewModel,
                uvm = userViewModel
            )
        }

//        Screen.MAP -> {
//            val latitude = navViewModel.mapLatitude
//            val longitude = navViewModel.mapLongitude
//
//            if (latitude != null && longitude != null) {
//                MapboxMapScreen(
//                    postLatitude = latitude,
//                    postLongitude = longitude,
//                    nav = navViewModel
//                )
//            }
//        }
    }
}