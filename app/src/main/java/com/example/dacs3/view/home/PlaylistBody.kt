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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dacs3.R
import com.example.dacs3.data.Playlist
import com.example.dacs3.data.Song
import com.example.dacs3.relation.PlaylistWithSongs
import com.example.dacs3.view.navigation.NavDestination

@Composable
fun PlaylistList(
    playlistSong: List<Playlist>,
    goToDetailPlaylist: () -> Unit
) {
    var isSongSelected by remember { mutableStateOf(false) }

    LazyRow(
        modifier = Modifier.padding(start = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(items = playlistSong, key = { it.playlist_id !!}) { playlist ->
            PlaylistItem(
                playlist = playlist,
                goToDetailPlaylist = goToDetailPlaylist,
            )
        }
    }
}
@Composable
fun PlaylistItem(
    playlist:Playlist,
    goToDetailPlaylist: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .width(120.dp)
            .clickable { }
    ) {

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
        ) {
            AsyncImage(
                model = playlist.playlist_image,
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
            text = playlist.playlist_name,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.width(120.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


