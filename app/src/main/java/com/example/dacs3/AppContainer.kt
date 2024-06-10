package com.example.dacs3

import android.content.Context
import com.example.dacs3.repository.MusicRepository

interface AppContainer {
    val musicRepository:MusicRepository
}
class AppDataContainer(private val context: Context):AppContainer{
    override val musicRepository: MusicRepository by lazy {
        MusicRepository(
            albumDao = MyDatabase.getDatabase(context).albumDao(),
            artistDao = MyDatabase.getDatabase(context).artistDao(),
            playlistDao = MyDatabase.getDatabase(context).playlistDao(),
            songDao = MyDatabase.getDatabase(context).songDao(),
            userDao = MyDatabase.getDatabase(context).userDao(),
            playlistSongDao =MyDatabase.getDatabase(context).songPlaylistDao()
        )
    }
}