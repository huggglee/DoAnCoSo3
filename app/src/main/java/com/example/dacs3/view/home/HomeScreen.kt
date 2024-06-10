package com.example.dacs3.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dacs3.view.navigation.NavDestination
import com.example.dacs3.view.scaffold.ScaffoldHome
import com.example.dacs3.viewModel.AppViewModelProvider
import com.example.dacs3.viewModel.HomeViewModel


object HomeScreen : NavDestination {
    override val route: String = "HomeScreen"
}

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    goToDetailArtist: (Int) -> Unit
) {
    val playlistList by homeViewModel.playlistUIState.collectAsState()
    val songList by homeViewModel.songUIState.collectAsState()
    val artistList by homeViewModel.singerUiState.collectAsState()
    val scroll = rememberScrollState()

    ScaffoldHome {
        Column(modifier = Modifier.verticalScroll(scroll).background(Color.Black)) {
            NavigationTitle(navTitle = "Ca sĩ nổi tiếng") {

            }
            ArtistList(
                artistList = artistList.artistList,
                goToDetailArtist = { goToDetailArtist(it) }
            )

            Spacer(modifier = Modifier.height(20.dp))

            NavigationTitle(navTitle = "Bài hát mới nhất") {

            }
            SongList(songList = songList.songList) {

            }
            Spacer(modifier = Modifier.height(20.dp))
            NavigationTitle(navTitle = "Album mới nhất") {

            }

            PlaylistList(playlistSong = playlistList.playlistList) {

            }
            Spacer(modifier = Modifier.height(20.dp))
            NavigationTitle(navTitle = "Album mới nhất") {

            }

            PlaylistList(playlistSong = playlistList.playlistList) {

            }
        }
    }
}

