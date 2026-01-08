package com.example.myapplication.feed.model

import android.util.Log
import com.example.myapplication.data.UsersExample
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.data.viewmodel.UserViewModel

data class FeedPostWrapper(
    val post: PostEntity,
    val author: UserEntity?, // Può essere null se non ancora caricato
    val isFollowing: Boolean = false
)

class FeedRepository(
    private val dao: UserDao
) {
    private var currentIndex = 0

    suspend fun getFeed(limit: Int = 10): List<FeedPostWrapper> {
        val posts = dao.getPagedPosts(limit, currentIndex)
        currentIndex += limit
        return posts.map { post ->
            FeedPostWrapper(
                post = post,
                author = dao.getUserById(post.authorId)
            )
        }
    }

    fun reset() {
        currentIndex = 0
    }
}

