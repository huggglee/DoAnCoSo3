package com.example.dacs3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.dacs3.data.Album
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Playlist
import com.example.dacs3.data.Song
import com.example.dacs3.relation.ArtistWithSongs
import com.example.dacs3.relation.PlaylistSongCrossRef
import com.example.dacs3.relation.SongArtistCrossRef
import com.example.dacs3.ui.theme.DACS3Theme
import com.example.dacs3.view.navigation.NavGraph
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DACS3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(navController = navController)
                }
            }

//            val playListDao = MyDatabase.getDatabase(this).playlistDao()
//            val songDao = MyDatabase.getDatabase(this).songDao()
//            val albumDao = MyDatabase.getDatabase(this).albumDao()
//            val artistDao = MyDatabase.getDatabase(this).artistDao()
//            val crossRef = MyDatabase.getDatabase(this).songPlaylistDao()
//            val songs = listOf(
//                Song(1,"song 1","link","",1L,1),
//                Song(2,"song 2","link","100L",2L,1),
//                Song(3,"song 3","link","100L",1L,1),
//            )
//            val artists = listOf(
//                Artist(0,"artist 1","",1,""),
//                Artist(0,"artist 2","",1,""),
//                Artist(0,"artist 3","",1,""),
//                Artist(0,"artist 4","",1,""),
//                Artist(0,"artist 5","",1,""),
//                Artist(0,"artist 6","",1,""),
//            )
//
//            val artistSongCrossRef  =  listOf(
//                SongArtistCrossRef(1,1),
//                SongArtistCrossRef(2,1),
//                SongArtistCrossRef(3,1),
//            )
//
//            val album = listOf(
//                 Album(1,"album1","",1),
//                 Album(2,"album2","",1),
//                 Album(3,"album3","",1),
//            )
//
//            val playLists = listOf(
//                Playlist(1,"PlayList 1","",1),
//                Playlist(2,"PlayList 2","",2),
//                Playlist(3,"PlayList 3","",3),
//            )
//
//            val songPlayCrossRef = listOf(
//                PlaylistSongCrossRef(1,1),
//                PlaylistSongCrossRef(2,1),
//                PlaylistSongCrossRef(3,1),
//                PlaylistSongCrossRef(3,2),
//                PlaylistSongCrossRef(1,2),
//            )
//
//            lifecycleScope.launch {
//                songs.forEach { songDao.insert(it) }
//                artists.forEach { artistDao.insert(it) }
//                album.forEach { albumDao.insert(it) }
//                playLists.forEach { playListDao.insert(it) }
//                songPlayCrossRef.forEach { crossRef.insertSongPlaylistCrossRef(it) }
//                artistSongCrossRef.forEach { artistDao.insertArtistSongCrossRef(it) }
//            }
        }
    }
}


