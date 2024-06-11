package com.example.dacs3.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Playlist
import com.example.dacs3.data.User
import com.example.dacs3.relation.SongWithArtists
import com.example.dacs3.repository.MusicRepository
import com.example.dacs3.view.home.HomeScreen
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val musicRepository: MusicRepository
) : ViewModel() {

    private val userID: Int = checkNotNull(savedStateHandle[HomeScreen.user_id])

    val userUiState: StateFlow<UserUiState> =
        musicRepository.getUserById(userID)
            .filterNotNull()
            .map {
                UserUiState(
                    user = it
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = UserUiState()
            )

    val singerUiState: StateFlow<ArtistUiState> =
        musicRepository.getAllArtists().map { ArtistUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ArtistUiState()
            )

    val songUIState: StateFlow<ArtistSongsUiState> =
        musicRepository.getSongWithArtists().map { ArtistSongsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ArtistSongsUiState()
            )

    val playlistUIState: StateFlow<PlaylistUIState> =
        musicRepository.getAllPlaylists().map { PlaylistUIState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PlaylistUIState()
            )
}

data class UserUiState(
    val user: User = User(0, "", "", "")
)

data class ArtistUiState(val artistList: List<Artist> = listOf())
data class ArtistSongsUiState(val songList: List<SongWithArtists> = listOf())

data class PlaylistUIState(val playlistList: List<Playlist> = listOf())

