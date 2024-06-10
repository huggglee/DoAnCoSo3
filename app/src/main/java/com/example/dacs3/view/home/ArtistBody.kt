package com.example.dacs3.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dacs3.R
import com.example.dacs3.data.Artist


@Composable
fun ArtistList(
    artistList: List<Artist>,
    goToDetailArtist: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(start = 18.dp)
            .background(Color.Black),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
    ) {
        items(artistList) { artist ->
            ArtistItem(
                artist = artist,
                goToDetailArtist = { goToDetailArtist(artist.artist_id) }
            )
        }
    }
}

@Composable
fun ArtistItem(
    artist: Artist,
    goToDetailArtist: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .width(66.dp)
            .height(96.dp)
            .clickable {
                goToDetailArtist(artist.artist_id)
            },
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(100))
                .background(Color.Black)
        ) {
            AsyncImage(
                model = artist.artist_image, contentDescription = "",
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
                    .align(Alignment.Center),
            )
        }
        Text(
            text = artist.artist_name,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.width(62.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}
