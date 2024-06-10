package com.example.dacs3.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Song
import com.example.dacs3.repository.MusicRepository
import com.example.dacs3.view.artistDetails.ArtistScreen
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ArtistDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val musicRepository: MusicRepository
) : ViewModel(

) {
    private val artist_id: Int = checkNotNull(savedStateHandle[ArtistScreen.artist_id])

    val uiState: StateFlow<ArtistDetailsUiState> =
        musicRepository.getSongbyArtistID(artist_id).map {
            ArtistDetailsUiState(
                artistDetails = ArtistDetails(
                    artist = it.artist,
                    songs = it.songs
                )
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ArtistDetailsUiState()
            )
}

data class ArtistDetailsUiState(
    val artistDetails: ArtistDetails = ArtistDetails()
)

data class ArtistDetails(
    val artist: Artist = Artist(0, "ffffffffffffffffffffff", "", 0, ""),
    val songs: List<Song> = listOf()
)