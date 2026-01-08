package com.example.myapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity
data class UserEntity (
    @PrimaryKey val id: Int,
    val createdAt: String,
    val username: String,
    val bio: String,
    val dateOfBirth: String,
    val profilePicture: String,
    val isYourFollower: Boolean,
    val isYourFollowing: Boolean,
    val followersCount: Int,
    val followingCount: Int,
    val postsCount: Int
)

@Entity
data class PostEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val authorId: Int,
    val createdAt: String,
    val contentPicture: String,
    val contentText: String,
    val location: Location
)

data class Location(
    val latitude: Double,
    val longitude: Double
)

//@Serializable
//data class UserDto(
//    val id: Int,
//    val createdAt: String,
//    val username: String,
//    val bio: String,
//    val dateOfBirth: String,
//    val profilePicture: String,
//    val isYourFollower: Boolean,
//    val isYourFollowing: Boolean,
//    val followersCount: Int,
//    val followingCount: Int,
//    val postsCount: Int
//)
//
//fun UserDto.toEntity(): UserEntity {
//    return UserEntity(
//        id = id,
//        createdAt = createdAt,
//        username = username,
//        bio = bio,
//        dateOfBirth = dateOfBirth,
//        profilePicture = profilePicture,
//        isYourFollower = isYourFollower,
//        isYourFollowing = isYourFollowing,
//        followersCount = followersCount,
//        followingCount = followingCount,
//        postsCount = postsCount
//    )
//}