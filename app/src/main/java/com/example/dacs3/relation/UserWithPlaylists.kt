package com.example.dacs3.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.dacs3.data.Playlist
import com.example.dacs3.data.User


data class UserWithPlaylists(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val playlists: List<Playlist>
)
