package com.example.dacs3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dacs3.data.Playlist
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(playlist: Playlist)

    @Update
    suspend fun update(playlist: Playlist)

    @Delete
    fun delete(playlist: Playlist)

    @Query("SELECT * FROM Playlist WHERE playlist_id = :id")
    fun getPlaylistById(id: Int): Playlist

    @Query("SELECT * FROM Playlist")
    fun getAllPlaylists(): Flow<List<Playlist>>
}
