package com.example.dacs3.viewModel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.dacs3.data.Song
import com.example.dacs3.repository.MusicRepository
import com.example.dacs3.view.songDetails.songScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SongDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val songsRepository: MusicRepository,
) : ViewModel() {

    var exoPlayer: ExoPlayer? = null

    val song_id: Int = checkNotNull(savedStateHandle[songScreen.song_id])

    private val _isPlaying = MutableStateFlow(false)
    var isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()


    val uiState: StateFlow<songUI> =
        songsRepository.getSongById(song_id).map {
            songUI(
                song = it
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = songUI()
        )

    fun play(source: String, context: Context) {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build()
        }
        var mediaItem = MediaItem.fromUri(Uri.parse(source))
        exoPlayer?.addMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.play()
    }

    fun isPlayingChange() {
        _isPlaying.value = !_isPlaying.value
    }
    fun pause(){
        exoPlayer?.pause()
    }
    fun resume(){
        exoPlayer?.play()
    }




}


data class songUI(
    val song: Song = Song(0, "", "", "", 0L, 0)
)