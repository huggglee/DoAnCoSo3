package com.example.dacs3.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Playlist
import com.example.dacs3.data.Song

@Entity(primaryKeys = ["song_id","artist_id"])
data class SongArtistCrossRef(
    val song_id:Int,
    val artist_id:Int
)

data class ArtistWithSongs(
    @Embedded val artist: Artist,
    @Relation(
        parentColumn = "artist_id",
        entityColumn = "song_id",
        associateBy = Junction(SongArtistCrossRef::class)
    )
    val songs: List<Song>
)

data class SongWithArtists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "song_id",
        entityColumn = "artist_id",
        associateBy = Junction(SongArtistCrossRef::class)
    )
    val artists: List<Artist>
)
