package com.example.dacs3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.dacs3.data.Album
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(album: Album)

    @Update
    suspend fun update(album: Album)

    @Delete
    fun delete(album: Album)

    @Query("SELECT * FROM Album WHERE album_id = :id")
    fun getAlbumById(id: Int): Album

    @Query("SELECT * FROM Album")
    fun getAllAlbums(): List<Album>

//    @Transaction
//    @Query("SELECT * FROM Song WHERE album_id = :id")
//    fun getAlbumWithSongs(id: Int): Flow<Al>

}
