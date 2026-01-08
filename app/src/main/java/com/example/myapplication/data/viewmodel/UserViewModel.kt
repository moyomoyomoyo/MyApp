package com.example.myapplication.data.viewmodel

import android.content.Context

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.DatabaseBuilder
import com.example.myapplication.data.UsersExample
import com.example.myapplication.data.createPostList
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.feed.view.formatDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {
    val userDao = DatabaseBuilder.getInstance(context).userDao()

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user: StateFlow<UserEntity?> = _user

    private val _posts = MutableStateFlow<List<PostEntity?>>(emptyList())
    val posts: StateFlow<List<PostEntity?>> = _posts

    private val _postId = MutableStateFlow<List<PostEntity?>>(emptyList())
    val postId: StateFlow<List<PostEntity?>> = _postId

    private val _postById = MutableStateFlow<PostEntity?>(null)
    val postById: StateFlow<PostEntity?> = _postById

    init{
        val moo = createPostList()
        val zz = UsersExample().users
        viewModelScope.launch {
            for(post in moo){
                userDao.insertPost(post)
            }

            for(user in zz){
                userDao.insertUser(user)
            }
        }
    }

    fun loadUser(userId: Int) {
        viewModelScope.launch {
            _user.value = userDao.getUserById(userId)
        }
    }

    fun saveUser(user: UserEntity) {
        viewModelScope.launch {
            userDao.insertUser(user)
            _user.value = user
        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch {
            userDao.updateUser(user)
            loadUser(user.id)
        }
    }

    fun insertPost(post: PostEntity) {
        viewModelScope.launch {
            userDao.insertPost(post)
            getAllPosts()
        }
    }

    fun getAllPosts() {
        viewModelScope.launch { _posts.value = userDao.getAllPosts() }
    }

    fun getPostsByUserId(userId: Int) {
        viewModelScope.launch {
            _postId.value = userDao.getPostsByUserId(userId)
        }
    }

    fun getPostById(postId: Int) {
        viewModelScope.launch { _postById.value = userDao.getPostById(postId) }
    }

    suspend fun getUserById(userId: Int): UserEntity? {
        return userDao.getUserById(userId)
    }

}