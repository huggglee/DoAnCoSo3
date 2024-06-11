package com.example.dacs3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dacs3.data.Song
import com.example.dacs3.relation.SongWithArtists
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(song: Song)

    @Update
    suspend fun update(song: Song)

    @Delete
    fun delete(song: Song)

    @Query("SELECT * FROM Song WHERE song_id = :id")
    fun getSongById(id: Int): Flow<Song>

    @Query("SELECT * FROM Song")
    fun getAllSongs(): List<Song>

    @Query(
        """
    SELECT s.*, ar.*, GROUP_CONCAT(ar.artist_name, ', ') AS artistNames
    FROM song s
    INNER JOIN songartistcrossref ref ON ref.song_id = s.song_id
    INNER JOIN artist ar ON ar.artist_id = ref.artist_id
    GROUP BY s.song_id, s.song_name
    """
    )
    fun getSongWithArtists(): Flow<List<SongWithArtists>>

}
