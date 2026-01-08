package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    suspend fun getUserById(id: Int): UserEntity

    @Update
    suspend fun updateUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity)

    @Query("SELECT * FROM PostEntity")
    suspend fun getAllPosts(): List<PostEntity>

    @Query("SELECT * FROM PostEntity WHERE authorId = :userId")
    suspend fun getPostsByUserId(userId: Int): List<PostEntity>

    @Query("SELECT * FROM PostEntity WHERE id = :postId")
    suspend fun getPostById(postId: Int): PostEntity

    @Query("SELECT * FROM PostEntity LIMIT :limit OFFSET :offset")
    suspend fun getPagedPosts(limit: Int, offset: Int): List<PostEntity>


}