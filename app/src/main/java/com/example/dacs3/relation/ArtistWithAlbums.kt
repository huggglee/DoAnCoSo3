package com.example.dacs3.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.dacs3.data.Album
import com.example.dacs3.data.Artist

data class ArtistWithAlbums(
    @Embedded val artist: Artist,
    @Relation(
        parentColumn = "artist_id",
        entityColumn = "artist_id"
    )
    val albums: List<Album>
)
