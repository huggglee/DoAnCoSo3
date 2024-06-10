package com.example.dacs3.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.dacs3.data.Playlist
import com.example.dacs3.data.Song

@Entity(tableName = "playlistSongCrossRef", primaryKeys = ["song_id","playlist_id"])
data class PlaylistSongCrossRef(
    val song_id:Int,
    val playlist_id:Int
)

data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "playlist_id",
        entityColumn = "song_id",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs: List<Song>
)

data class SongWithPlaylists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "song_id",
        entityColumn = "playlist_id",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val playlists: List<Playlist>
)

