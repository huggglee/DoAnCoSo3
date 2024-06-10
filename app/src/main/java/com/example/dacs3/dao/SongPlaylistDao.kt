package com.example.dacs3.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.dacs3.data.Playlist
import com.example.dacs3.relation.PlaylistSongCrossRef
import com.example.dacs3.relation.PlaylistWithSongs
import com.example.dacs3.relation.SongWithPlaylists

@Dao
interface SongPlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongPlaylistCrossRef(crossRef: PlaylistSongCrossRef)


}