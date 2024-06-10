package com.example.dacs3.view.artistDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.dacs3.R
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Song
import com.example.dacs3.view.home.stringBuilder
import com.example.dacs3.view.navigation.NavDestination
import com.example.dacs3.view.scaffold.ScaffoldHome
import com.example.dacs3.viewModel.AppViewModelProvider
import com.example.dacs3.viewModel.ArtistDetailsViewModel

object ArtistScreen : NavDestination {
    override val route: String = "SingerScreen"
    const val artist_id = "artist_id"
    val routeWithId = "$route/{$artist_id}"
}

@Composable
fun ArtistScreen(
    artistViewModel: ArtistDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val artistUiState by artistViewModel.uiState.collectAsState()

    ScaffoldHome {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            item {
                imgSinger(artist = artistUiState.artistDetails.artist)
            }

            items(artistUiState.artistDetails.songs) { song ->
                SongItems(song = song, artist = artistUiState.artistDetails.artist)
            }

            item {
                singerInfor(artist = artistUiState.artistDetails.artist)
            }
        }
    }
}

@Composable
fun imgSinger(
    artist: Artist
) {
    Box(modifier = Modifier.height(400.dp)) {

        AsyncImage(
            model = artist.artist_image,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {

            Text(
                text = artist.artist_name,
                fontSize = 25.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif
            )
            Text(
                text = "999 luot",
                fontSize = 17.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.LightGray
            )

        }
    }
}

@Composable
fun SongItems(
    song: Song,
    artist: Artist
) {
    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 15.dp, horizontal = 20.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = song.song_image,
                contentDescription = null,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .background(Color.Black)
                    .size(68.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = song.song_name,
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = artist.artist_name,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Filled.MoreHoriz, contentDescription = null)
        }
    }
}

@Composable
fun singerInfor(
    artist: Artist
) {
    var click by rememberSaveable {
        mutableStateOf(false)
    }
    var maxLine by rememberSaveable {
        mutableStateOf(3)
    }
    Column(modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp)) {
        Text(
            text = "Thông tin",
            fontFamily = FontFamily.SansSerif,
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(R.string.singerInfor),
            maxLines = maxLine,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .clickable {
                    click = !click
                    if (click) {
                        maxLine = 30
                    } else maxLine = 3
                }
        )
        Row {
            Text(text = "Tên thật", color = Color.Gray)
            Spacer(modifier = Modifier.width(40.dp))
            Text(text = artist.artist_name, color = Color.White)
        }
        Row {
            Text(text = "Tuổi", color = Color.Gray)
            Spacer(modifier = Modifier.width(40.dp))
            Text(text = artist.artist_age.toString(), color = Color.White)
        }
        Row {
            Text(text = "Quốc gia", color = Color.Gray)
            Spacer(modifier = Modifier.width(40.dp))
            Text(text = "Việt Nam", color = Color.White)
        }
        Row {
            Text(text = "Thể loại", color = Color.Gray)
            Spacer(modifier = Modifier.width(40.dp))
            Text(text = "Bollero, trữ tình", color = Color.White)
        }
    }
}