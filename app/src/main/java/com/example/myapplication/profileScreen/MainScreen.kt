package com.example.myapplication.profileScreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.myapplication.components.MainNavigationSetup
import com.example.myapplication.data.viewmodel.UserViewModel
import com.example.myapplication.feed.view.FeedScreen
import com.example.myapplication.navigation.NavigationViewModel
import com.example.myapplication.navigation.Screen
import com.example.myapplication.post.PostDetail

@Composable
fun MainScreen(nav: NavigationViewModel, uvm: UserViewModel, modifier: Modifier) {

    LaunchedEffect(Unit) {
        uvm.loadUser(0)
        uvm.getAllPosts()
        uvm.getPostsByUserId(0)
    }
    val user by uvm.user.collectAsState()
    val posts by uvm.posts.collectAsState()
    val userPosts by uvm.postId.collectAsState()

    Log.i("MainScreenfeed", "Rendo  ${posts.size} post nel MainScreen")

    user?.let { MainNavigationSetup(nav, uvm, it.id) }

    when (nav.currentScreen) {
        Screen.FEED -> FeedScreen(posts, user, nav, uvm)
        Screen.PROFILE -> ProfileScreen(user, nav, userPosts, uvm, modifier)
        Screen.CREATE_POST -> CreatePostScreen(nav, uvm)
        Screen.PROFILE_SETTINGS -> ProfileSettings(user, nav, uvm)
        Screen.POST_DETAIL -> {
            nav.selectedPostId?.let { postId ->
                PostDetail(
                    postId = postId,
                    nav = nav,
                    uvm = uvm
                )
            }
        }
    }
}