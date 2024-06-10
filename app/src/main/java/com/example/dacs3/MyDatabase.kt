package com.example.dacs3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dacs3.dao.AlbumDao
import com.example.dacs3.dao.ArtistDao
import com.example.dacs3.dao.PlaylistDao
import com.example.dacs3.dao.SongDao
import com.example.dacs3.dao.SongPlaylistDao
import com.example.dacs3.dao.UserDao
import com.example.dacs3.data.Album
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Playlist
import com.example.dacs3.data.Song
import com.example.dacs3.data.User
import com.example.dacs3.relation.PlaylistSongCrossRef
import com.example.dacs3.relation.SongArtistCrossRef


@Database(
    entities = [
        Album::class,
        Artist::class,
        User::class,
        Playlist::class,
        Song::class,
        PlaylistSongCrossRef::class,
        SongArtistCrossRef::class
               ],
    version = 1
)
abstract class MyDatabase :RoomDatabase(){
    abstract fun albumDao(): AlbumDao
    abstract fun artistDao(): ArtistDao
    abstract fun userDao(): UserDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun songDao(): SongDao
    abstract fun songPlaylistDao(): SongPlaylistDao


    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "demo5"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}