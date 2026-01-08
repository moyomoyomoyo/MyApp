package com.example.myapplication.data

import com.example.myapplication.data.entity.Location
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.entity.UserEntity
import com.example.myapplication.post.Post
import java.time.LocalDateTime

class UsersExample() {
    val users = listOf(
        UserEntity(
            id = 1,
            createdAt = "2023-01-01T12:00:00Z",
            username = "johndoe",
            bio = "Lover of tech and video games!",
            dateOfBirth = "2000-05-15",
            profilePicture = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAAD8VhOfAAAAmklEQVR42mNkYPjv4tKFcqXwGlwM0cAOzG9Lh",
            isYourFollower = true,
            isYourFollowing = false,
            followersCount = 120,
            followingCount = 150,
            postsCount = 30
        ),
        UserEntity(
            id = 2,
            createdAt = "2023-02-20T14:15:00Z",
            username = "janedoe",
            bio = "I love photography and traveling the world 🌍",
            dateOfBirth = "1998-08-25",
            profilePicture = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAAD8VhOfAAAAmklEQVR42mNkYPjv4tKFcqXwGlwM0cAOzG9Lh+R6AN4pmzRBDjA0XzA5d0z3A50W0eF4+M1h1FCW5O2lhADJNStg9hvFA5B54nD5jw02sH0tFHZ2w5D/jCw2F6A1JWeP1hOZ5e7R2CgBuHryvft1FlRVwL72AQzIQ8UuQGomq6ITCxcq6C2Yl9tLUeaCUHzlZmZ8d9hP+apckhZy8wX1gqdK2Vnuw38DVLl1nvptZnQPoC1r5g6tKEm9uOYZYhbjCm5yOHZgKNzTBrSkMu2Hrx4bCmUO2v+WFG7OegZoCm1nI2VzWa00By3yX4yG7ryyCVAT9zZzXMyj2wS4MmdZBzAkYAnKAK2NmC/ybTfh7i0aU9SOw==",
            isYourFollower = false,
            isYourFollowing = true,
            followersCount = 200,
            followingCount = 180,
            postsCount = 50
        ),
        UserEntity(
            id = 3,
            createdAt = "2023-03-10T09:45:00Z",
            username = "alexsmith",
            bio = "Coffee, coding, and cats 😸☕💻",
            dateOfBirth = "2002-12-05",
            profilePicture = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAAD8VhOfAAAAmklEQVR42mNkYPjv4tKFcqXwGlwM0cAOzG9Lh+R6AN4pmzRBDjA0XzA5d0z3A50W0eF4+M1h1FCW5O2lhADJNStg9hvFA5B54nD5jw02sH0tFHZ2w5D/jCw2F6A1JWeP1hOZ5e7R2CgBuHryvft1FlRVwL72AQzIQ8UuQGomq6ITCxcq6C2Yl9tLUeaCUHzlZmZ8d9hP+apckhZy8wX1gqdK2Vnuw38DVLl1nvptZnQPoC1r5g6tKEm9uOYZYhbjCm5yOHZgKNzTBrSkMu2Hrx4bCmUO2v+WFG7OegZoCm1nI2VzWa00By3yX4yG7ryyCVAT9zZzXMyj2wS4MmdZBzAkYAnKAK2NmC/ybTfh7i0aU9SOw==",
            isYourFollower = true,
            isYourFollowing = true,
            followersCount = 350,
            followingCount = 300,
            postsCount = 80
        ),
        UserEntity(
            id = 4,
            createdAt = "2023-03-10T09:45:00Z",
            username = "moyofoyo",
            bio = "Coffee, coding, and cats 😸☕💻",
            dateOfBirth = "2002-12-02",
            profilePicture = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAIAAAD8VhOfAAAAmklEQVR42mNkYPjv4tKFcqXwGlwM0cAOzG9Lh+R6AN4pmzRBDjA0XzA5d0z3A50W0eF4+M1h1FCW5O2lhADJNStg9hvFA5B54nD5jw02sH0tFHZ2w5D/jCw2F6A1JWeP1hOZ5e7R2CgBuHryvft1FlRVwL72AQzIQ8UuQGomq6ITCxcq6C2Yl9tLUeaCUHzlZmZ8d9hP+apckhZy8wX1gqdK2Vnuw38DVLl1nvptZnQPoC1r5g6tKEm9uOYZYhbjCm5yOHZgKNzTBrSkMu2Hrx4bCmUO2v+WFG7OegZoCm1nI2VzWa00By3yX4yG7ryyCVAT9zZzXMyj2wS4MmdZBzAkYAnKAK2NmC/ybTfh7i0aU9SOw==",
            isYourFollower = true,
            isYourFollowing = true,
            followersCount = 350,
            followingCount = 300,
            postsCount = 80
        )
    )
}

fun createPostList(): List<PostEntity> {
    return (1..50).map { index ->
        PostEntity(
            id = index,
            authorId = (1..4).random(),
            contentPicture = "",
            contentText = "Post numero $index",
            createdAt = LocalDateTime.now().toString(),
            location = Location(
                latitude = 0.0,
                longitude = 0.0
            )
        )
    }

}
