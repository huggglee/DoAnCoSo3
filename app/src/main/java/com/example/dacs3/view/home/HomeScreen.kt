package com.example.dacs3.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dacs3.R
import com.example.dacs3.ui.theme.Gray1
import com.example.dacs3.view.navigation.NavDestination
import com.example.dacs3.view.scaffold.BottomAppBar
import com.example.dacs3.view.scaffold.ContentTopAppBar
import com.example.dacs3.viewModel.AppViewModelProvider
import com.example.dacs3.viewModel.HomeViewModel
import kotlinx.coroutines.launch


object HomeScreen : NavDestination {
    override val route: String = "HomeScreen"
    const val user_id = "user_id"
    val routeWithArgs = "$route/{$user_id}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    goToDetailArtist: (Int) -> Unit,
    goToSongDetails: (Int) -> Unit,

    ) {

    val userUiState by homeViewModel.userUiState.collectAsState()
    val playlistList by homeViewModel.playlistUIState.collectAsState()
    val songList by homeViewModel.songUIState.collectAsState()
    val artistList by homeViewModel.singerUiState.collectAsState()
    val scroll = rememberScrollState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var isFavourite by rememberSaveable {
        mutableStateOf(false)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.Black,
                drawerContentColor = Color.White,
                modifier = Modifier.width(300.dp),

                ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.chungtacuatuonglai_mtp),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = RoundedCornerShape(100))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
//                        User name
                        Text(
                            text = userUiState.user.fullName,
                            modifier = Modifier,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Xem hồ sơ",
                            modifier = Modifier,
                            fontSize = 12.sp,
                            color = Gray1
                        )
                    }
                }
                Divider()
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /*TODO()*/ }
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.LibraryBooks,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Playlist của bạn", modifier = Modifier,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /*TODO()*/ }
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Timelapse,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Nội dung gần đây", modifier = Modifier,
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                        }
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Đăng xuất", modifier = Modifier,
                        fontSize = 18.sp
                    )
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        ContentTopAppBar(
                            scope,
                            drawerState,
                            userUiState.user
                        )
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    onClickFavourite = { isFavourite = !isFavourite },
                    isFavourite = isFavourite,
                    goToHomeScreen = { },
                    goToSearchScreen = { },
                    goToAccountScreen = { },
                    goToPlaylistScreen = {},
                )
            },

            ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                NavigationTitle(navTitle = "Ca sĩ nổi tiếng") {
                }
                ArtistList(
                    artistList = artistList.artistList,
                    goToDetailArtist = { goToDetailArtist(it) }
                )

                NavigationTitle(navTitle = "Bài hát mới nhất") {
                }
                SongList(
                    songList = songList.songList,
                    goToSongDetails = { goToSongDetails(it) }
                )

                NavigationTitle(navTitle = "Album mới nhất") {
                }
                PlaylistList(playlistSong = playlistList.playlistList) {
                }
            }
        }
    }

//    ScaffoldHome(userUiState.user) {
//        Column(
//            modifier = Modifier
//                .verticalScroll(scroll)
//                .background(Color.Black)
//        ) {
//            NavigationTitle(navTitle = "Ca sĩ nổi tiếng") {
//
//            }
//            ArtistList(
//                artistList = artistList.artistList,
//                goToDetailArtist = { goToDetailArtist(it) }
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            NavigationTitle(navTitle = "Bài hát mới nhất") {
//
//            }
//
//            SongList(
//                songList = songList.songList,
//                goToSongDetails = { goToSongDetails(it) }
//            )
//
//
//            Spacer(modifier = Modifier.height(20.dp))
//            NavigationTitle(navTitle = "Album mới nhất") {
//
//            }
//
//            PlaylistList(playlistSong = playlistList.playlistList) {
//
//            }
//            Spacer(modifier = Modifier.height(20.dp))
//            NavigationTitle(navTitle = "Album mới nhất") {
//
//            }
//
//            PlaylistList(playlistSong = playlistList.playlistList) {
//
//            }
//        }
//    }
}


