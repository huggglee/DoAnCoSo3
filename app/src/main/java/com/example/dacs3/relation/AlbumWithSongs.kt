package com.example.dacs3.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.dacs3.data.Album
import com.example.dacs3.data.Song

data class AlbumWithSongs(
    @Embedded val album: Album,
    @Relation(
        parentColumn = "album_id",
        entityColumn = "album_id"
    )
    val songs: List<Song>
)
