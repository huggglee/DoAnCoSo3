package com.example.dacs3.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "song",
)
data class Song(
    @PrimaryKey(autoGenerate = true) val song_id: Int = 0,
    val song_name: String,
    val song_image: String,
    val source: String,
    val duration: Long,
    val album_id: Int
)
