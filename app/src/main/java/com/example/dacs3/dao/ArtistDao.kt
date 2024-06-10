package com.example.dacs3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.dacs3.data.Artist
import com.example.dacs3.relation.ArtistWithSongs
import com.example.dacs3.relation.PlaylistSongCrossRef
import com.example.dacs3.relation.SongArtistCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(artist: Artist)

    @Update
    suspend fun update(artist: Artist)

    @Delete
    fun delete(artist: Artist)

    @Query("SELECT * FROM Artist WHERE artist_id = :id")
    fun getArtistById(id: Int): Artist

    @Query("SELECT * FROM Artist")
    fun getAllArtists(): Flow<List<Artist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtistSongCrossRef(crossRef: SongArtistCrossRef)
    @Transaction
    @Query(
        """
    SELECT ar.*, s.*,GROUP_CONCAT(s.song_name, ', ') AS songNames
    FROM artist ar
    Left JOIN SongArtistCrossRef ref ON ref.artist_id = ar.artist_id
    Left JOIN song s ON s.song_id = ref.song_id
    WHERE ar.artist_id = :Id
    GROUP BY ar.artist_id,ar.artist_name 
    """
    )
    fun getSongbyArtistID(Id:Int): Flow<ArtistWithSongs>

//    @Transaction
//    @Query("SELECT * FROM Artist WHERE artistId = :id")
//    fun getArtistWithAlbums(id: Int): ArtistWithAlbums
}
