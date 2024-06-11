package com.example.dacs3.view.songDetails

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.dacs3.R
import com.example.dacs3.data.Song
import com.example.dacs3.view.navigation.NavDestination
import com.example.dacs3.view.scaffold.TopBarOption
import com.example.dacs3.view.seekBar.SeekBar
import com.example.dacs3.viewModel.AppViewModelProvider
import com.example.dacs3.viewModel.SongDetailsViewModel

object songScreen : NavDestination {
    override val route: String = "songScreen"
    const val song_id = "song_id"
    val routeWithId = "$route/{$song_id}"
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SongDetailsScreen(
    viewModel: SongDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    goBackEvent: () -> Unit,
    goShareEvent: () -> Unit,
    goOptionEvent: () -> Unit,
) {
//
    var isFavourite by rememberSaveable {
        mutableStateOf(false)
    }
    val isPlaying = viewModel.isPlaying.collectAsState()
    val uiState = viewModel.uiState.collectAsState()

    val context: Context = LocalContext.current


    Scaffold(
        topBar = {
            TopBarOption(
                goBackEvent = goBackEvent,
                goShareEvent = goShareEvent,
                goOptionEvent = goOptionEvent,
                Modifier
                    .background(Color(0x66000000))
                    .fillMaxWidth()
            )

        }
    ) { valuePadding ->
        Column(modifier = Modifier.padding(valuePadding)) {

            SongDetailsBody(
                songDetails = uiState.value.song,
                progress = 0f,
                onProgressChange = {},
                isPlaying = isPlaying.value,
                playingEvent = {
                    viewModel.isPlayingChange()
                    viewModel.play(uiState.value.song.source, context)
                    if (isPlaying.value){
                        viewModel.pause()
                    }
                },
                isFavourite = isFavourite,
                favouriteEvent = {},
                duration = "",
                onLoading = true,
                goTo10s = {},
                previousTo10s = {},
                skipNext = {}
            )
        }
    }
}

@Composable
fun SongDetailsBody(
    songDetails: Song,
    modifier: Modifier = Modifier,
    progress: Float,
    onProgressChange: (Float) -> Unit,
    isPlaying: Boolean,
    playingEvent: () -> Unit,
    isFavourite: Boolean,
    favouriteEvent: () -> Unit,
    duration: String,
    goTo10s: () -> Unit,
    previousTo10s: () -> Unit,
    onLoading: Boolean,
    skipNext: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SongDetails(
            song = songDetails,
            progress = progress,
            onProgressChange = onProgressChange,
            isPlaying = isPlaying,
            playingEvent = playingEvent,
            isFavourite = isFavourite,
            favouriteEvent = favouriteEvent,
            duration = duration,
            onLoading = onLoading,
            goTo10s = goTo10s,
            previousTo10s = previousTo10s,
            skipNext = skipNext
        )
    }
}

@Composable
fun SongDetails(
    song: Song,
    progress: Float,
    onProgressChange: (Float) -> Unit,
    isPlaying: Boolean,
    playingEvent: () -> Unit,
    isFavourite: Boolean,
    favouriteEvent: () -> Unit,
    duration: String,
    onLoading: Boolean,
    goTo10s: () -> Unit,
    skipNext: () -> Unit,
    previousTo10s: () -> Unit,
) {

    Box(modifier = Modifier) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = song.song_image,
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(350.dp)
                        .clip(CircleShape)
                        .graphicsLayer {
                            rotationZ = progress
                        },
                )
                Icon(
                    imageVector = Icons.Filled.FiberManualRecord,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x6A454545))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            SeekBar(
                progress = progress,
                onProgressChange = { onProgressChange(it) },
                isPlaying = isPlaying,
                playingEvent = {
                    playingEvent()
                },
                isFavourite = isFavourite,
                favouriteEvent = { favouriteEvent() },
                songName = song.song_name,
                duration = duration,
                onValueChangeFinished = {},
                onLoading = onLoading,
                goTo10s = goTo10s,
                previousTo10s = previousTo10s,
                skipNext = skipNext

            )

        }
    }
}

//@Composable
//fun stringBuilder(singers: List<Artist>): String {
//    val builder = StringBuilder()
//
//    singers.forEachIndexed { index, artist ->
//        builder.append(artist.artist_name)
//        if (index < singers.size - 1) {
//            builder.append(", ")
//        }
//    }
//
//    return builder.toString()
//
//}
