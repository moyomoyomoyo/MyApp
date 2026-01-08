package com.example.myapplication.data.entity

import androidx.room.TypeConverter

class LocationConverter {

    @TypeConverter
    fun fromLocation(location: Location): String {
        return "${location.latitude},${location.longitude}"
    }

    @TypeConverter
    fun toLocation(data: String): Location {
        val parts = data.split(",")
        return Location(parts[0].toDouble(), parts[1].toDouble())
    }
}
