package com.example.dacs3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.dacs3.data.Album
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Playlist
import com.example.dacs3.data.Song
import com.example.dacs3.relation.ArtistWithSongs
import com.example.dacs3.relation.PlaylistSongCrossRef
import com.example.dacs3.relation.SongArtistCrossRef
import com.example.dacs3.ui.theme.DACS3Theme
import com.example.dacs3.view.navigation.NavGraph
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DACS3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(navController = navController)
                }
            }

            val songDao = MyDatabase.getDatabase(this).songDao()
            val albumDao = MyDatabase.getDatabase(this).albumDao()
            val artistDao = MyDatabase.getDatabase(this).artistDao()

            val artists = listOf(
                Artist(
                    0,
                    "B ray",
                    20,
                    "https://unwearable-beans.000webhostapp.com/Image/Artist/Bray.jpg",
                    "Bray, tên thật là Trần Tiến Đạt, là một rapper và nhà sản xuất âm nhạc nổi tiếng trong cộng đồng hip-hop Việt Nam. Anh đã đóng góp quan trọng vào sự phát triển và phổ biến nhạc rap tại Việt Nam."
                ),
                Artist(
                    0,
                    "Châu Khải Phong",
                    20,
                    "https://unwearable-beans.000webhostapp.com/Image/Artist/ChauKhaiPhong.jpg",
                    "Bray, tên thật là Trần Tiến Đạt, là một rapper và nhà sản xuất âm nhạc nổi tiếng trong cộng đồng hip-hop Việt Nam. Anh đã đóng góp quan trọng vào sự phát triển và phổ biến nhạc rap tại Việt Nam."
                ),
                Artist(
                    0,
                    "Đạt G",
                    20,
                    "https://unwearable-beans.000webhostapp.com/Image/Artist/DatG.jpg",
                    "Bray, tên thật là Trần Tiến Đạt, là một rapper và nhà sản xuất âm nhạc nổi tiếng trong cộng đồng hip-hop Việt Nam. Anh đã đóng góp quan trọng vào sự phát triển và phổ biến nhạc rap tại Việt Nam."
                ),
                Artist(
                    0,
                    "Sơn Tùng MTP",
                    20,
                    "https://unwearable-beans.000webhostapp.com/Image/Artist/SonTungMTP.jpg",
                    "Bray, tên thật là Trần Tiến Đạt, là một rapper và nhà sản xuất âm nhạc nổi tiếng trong cộng đồng hip-hop Việt Nam. Anh đã đóng góp quan trọng vào sự phát triển và phổ biến nhạc rap tại Việt Nam."
                ),
                Artist(
                    0,
                    "Phan Mạnh Quỳnh",
                    20,
                    "https://unwearable-beans.000webhostapp.com/Image/Artist/PhanManhQuynh.jpg",
                    "Bray, tên thật là Trần Tiến Đạt, là một rapper và nhà sản xuất âm nhạc nổi tiếng trong cộng đồng hip-hop Việt Nam. Anh đã đóng góp quan trọng vào sự phát triển và phổ biến nhạc rap tại Việt Nam."
                ),
                Artist(
                    0,
                    "Quân AP",
                    20,
                    "https://unwearable-beans.000webhostapp.com/Image/Artist/QuanAP.jpg",
                    "Bray, tên thật là Trần Tiến Đạt, là một rapper và nhà sản xuất âm nhạc nổi tiếng trong cộng đồng hip-hop Việt Nam. Anh đã đóng góp quan trọng vào sự phát triển và phổ biến nhạc rap tại Việt Nam."
                ),
                Artist(
                    0,
                    "Rhymastic",
                    20,
                    "https://unwearable-beans.000webhostapp.com/Image/Artist/Rhymastic.jpg",
                    "Bray, tên thật là Trần Tiến Đạt, là một rapper và nhà sản xuất âm nhạc nổi tiếng trong cộng đồng hip-hop Việt Nam. Anh đã đóng góp quan trọng vào sự phát triển và phổ biến nhạc rap tại Việt Nam."
                ),
                Artist(
                    0,
                    "Mr Siro",
                    20,
                    "https://unwearable-beans.000webhostapp.com/Image/Artist/MrSiro.jpg",
                    "Bray, tên thật là Trần Tiến Đạt, là một rapper và nhà sản xuất âm nhạc nổi tiếng trong cộng đồng hip-hop Việt Nam. Anh đã đóng góp quan trọng vào sự phát triển và phổ biến nhạc rap tại Việt Nam."
                ),
            )

            val songs = listOf(
                Song(
                    0,
                    "Cao ốc 20",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "https://unwearable-beans.000webhostapp.com/song/CaoOc20.mp3",
                    1,
                    0
                ),
                Song(
                    0,
                    "Lửng lơ",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/LungLo.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Thiêu thân",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/ThieuThan.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Ân tình sang trang",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/ChauKhaiPhong/AnTinhSangTrang.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Sợ ta mất nhau",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/ChauKhaiPhong/SoTaMatNhau.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Thương em",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/ChauKhaiPhong/ThuongEm.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Bánh mì không",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/DatG/BanhMiKhong.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Buồn của anh",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/DatG/BuonCuaAnh.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Thú tội",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/DatG/ThuToi.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Bức tranh từ nước mắt",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/MrSiro/BTTNM.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Càng níu giữ càng dễ mất",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/MrSiro/CNGCDM.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Một bước yêu vạn dặm đau",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/MrSiro/MBYVDD.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Có chàng trai viết lên cây",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/PhanManhQuynh/CCTVLC.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Nhạt",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Sao cha không",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Ai là người thương em",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Bông hoa đẹp nhất",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Lời xin lỗi vụng về",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Cứ chill thôi",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Nến và hoa",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Yêu 5",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Buông đôi tay nhau ra",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Chúng ta không thuộc về nhau",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
                Song(
                    0,
                    "Hãy trao cho anh",
                    "https://unwearable-beans.000webhostapp.com/Image/Song/Bray/CaoOc20.jpg",
                    "",
                    1,
                    0
                ),
            )

//          val playListDao = MyDatabase.getDatabase(this).playlistDao()


            val artistSongCrossRef = listOf(
                SongArtistCrossRef(1, 1),
                SongArtistCrossRef(2, 1),
                SongArtistCrossRef(3, 1),
                SongArtistCrossRef(4, 2),
                SongArtistCrossRef(5, 2),
                SongArtistCrossRef(6, 2),
                SongArtistCrossRef(7, 3),
                SongArtistCrossRef(8, 3),
                SongArtistCrossRef(9, 3),
                SongArtistCrossRef(10, 4),
                SongArtistCrossRef(11, 4),
                SongArtistCrossRef(12, 4),
                SongArtistCrossRef(13, 5),
                SongArtistCrossRef(14, 5),
                SongArtistCrossRef(15, 5),
                SongArtistCrossRef(16, 6),
                SongArtistCrossRef(17, 6),
                SongArtistCrossRef(18, 6),
                SongArtistCrossRef(19, 7),
                SongArtistCrossRef(20, 7),
                SongArtistCrossRef(21, 7),
                SongArtistCrossRef(22, 8),
                SongArtistCrossRef(23, 8),
                SongArtistCrossRef(24, 8),
            )

            val album = listOf(
                Album(0, "album1", "", 1),
                Album(0, "album2", "", 2),
                Album(0, "album3", "", 3),
                Album(0, "album4", "", 4),
                Album(0, "album5", "", 5),
                Album(0, "album6", "", 6),
                Album(0, "album7", "", 7),
                Album(0, "album8", "", 8),
            )

//            val playLists = listOf(
//                Playlist(1,"PlayList 1","",1),
//                Playlist(2,"PlayList 2","",2),
//                Playlist(3,"PlayList 3","",3),
//            )
//
//            val songPlayCrossRef = listOf(
//                PlaylistSongCrossRef(1,1),
//                PlaylistSongCrossRef(2,1),
//                PlaylistSongCrossRef(3,1),
//                PlaylistSongCrossRef(3,2),
//                PlaylistSongCrossRef(1,2),
//            )
//
            lifecycleScope.launch {
                songs.forEach { songDao.insert(it) }
                artists.forEach { artistDao.insert(it) }
                album.forEach { albumDao.insert(it) }
                artistSongCrossRef.forEach { artistDao.insertArtistSongCrossRef(it) }
//                playLists.forEach { playListDao.insert(it) }
//                songPlayCrossRef.forEach { crossRef.insertSongPlaylistCrossRef(it) }
//                artistSongCrossRef.forEach { artistDao.insertArtistSongCrossRef(it) }
            }
        }
    }
}


