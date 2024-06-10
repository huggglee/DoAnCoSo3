package com.example.dacs3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
data class Artist(
    @PrimaryKey(autoGenerate = true) val artist_id: Int = 0,
    val artist_name: String,
    val artist_gender: String,
    val artist_age: Int,
    val artist_image: String,
)
