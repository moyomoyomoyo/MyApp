package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entity.LocationConverter
import com.example.myapplication.data.entity.PostEntity
import com.example.myapplication.data.entity.UserEntity

@Database(entities = [UserEntity::class, PostEntity::class], version = 4, exportSchema = false)
@TypeConverters(LocationConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
