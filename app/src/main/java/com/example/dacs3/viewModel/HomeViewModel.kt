package com.example.dacs3.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Playlist
import com.example.dacs3.relation.ArtistWithSongs
import com.example.dacs3.relation.PlaylistWithSongs
import com.example.dacs3.relation.SongWithArtists
import com.example.dacs3.repository.MusicRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val musicRepository: MusicRepository
):ViewModel(){
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

data class ArtistUiState(val artistList: List<Artist> = listOf())
data class ArtistSongsUiState(val songList: List<SongWithArtists> = listOf())

data class PlaylistUIState(val playlistList: List<Playlist> = listOf())

