package com.example.dacs3.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "album"
)
data class Album(
    @PrimaryKey val album_id: Int = 0,
    val album_name: String,
    val album_image: String,
    val artist_id: Int
)
