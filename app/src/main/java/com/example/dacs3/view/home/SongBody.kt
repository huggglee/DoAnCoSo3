package com.example.dacs3.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dacs3.R
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Song
import com.example.dacs3.relation.SongWithArtists


@Composable
fun SongList(
    songList: List<SongWithArtists>,
    modifier: Modifier = Modifier,
    goToSongDetails: () -> Unit,
) {
    var isSongSelected by remember { mutableStateOf(false) }

    LazyRow(
        modifier = modifier.padding(start = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(items = songList, key = { it.song.song_id !!}) { song ->
            SongItem(
                song = song.song,
                artists = song.artists,
                goToDetailSong = { goToSongDetails() },
            )
        }
    }
}

@Composable
fun SongItem(
    song: Song,
    artists: List<Artist>,
    goToDetailSong: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .width(120.dp)
            .clickable { goToDetailSong() }
    ) {

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
        ) {
            AsyncImage(
                model = song.song_image,
                contentDescription = null,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
                    .align(Alignment.Center),
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = song.song_name,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.width(120.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = stringBuilder(artists),
            fontSize = 10.sp,
            color = Color(0xff808080),
            modifier = Modifier.width(120.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )


    }
}

@Composable
fun stringBuilder(artists: List<Artist>): String {
    val builder = StringBuilder()

    artists.forEachIndexed { index, artist ->
        builder.append(artist.artist_name)
        if (index < artists.size - 1) {
            builder.append(", ")
        }
    }

    return builder.toString()
}