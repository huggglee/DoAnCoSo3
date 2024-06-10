package com.example.dacs3.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "playlist",
)
data class Playlist(
    @PrimaryKey(autoGenerate = true) val playlist_id: Int = 0,
    val playlist_name: String,
    val playlist_image: String,
    val user_id: Int
)