package com.example.dacs3.repository

import com.example.dacs3.dao.AlbumDao
import com.example.dacs3.dao.ArtistDao
import com.example.dacs3.dao.PlaylistDao
import com.example.dacs3.dao.SongDao
import com.example.dacs3.dao.SongPlaylistDao
import com.example.dacs3.dao.UserDao
import com.example.dacs3.data.Album
import com.example.dacs3.data.Artist
import com.example.dacs3.data.Playlist
import com.example.dacs3.data.Song
import com.example.dacs3.data.User
import com.example.dacs3.relation.ArtistWithSongs
import com.example.dacs3.relation.PlaylistSongCrossRef
import com.example.dacs3.relation.PlaylistWithSongs
import com.example.dacs3.relation.SongArtistCrossRef
import com.example.dacs3.relation.SongWithArtists
import com.example.dacs3.relation.SongWithPlaylists
import kotlinx.coroutines.flow.Flow

class MusicRepository(
    private val albumDao: AlbumDao,
    private val artistDao: ArtistDao,
    private val playlistDao: PlaylistDao,
    private val songDao: SongDao,
    private val userDao: UserDao,
    private val playlistSongDao: SongPlaylistDao,

) : AlbumDao, ArtistDao, PlaylistDao, SongDao, UserDao,SongPlaylistDao {
    override suspend fun insert(album: Album) {
        albumDao.insert(album)
    }

    override suspend fun update(album: Album) {
        albumDao.update(album)
    }

    override fun delete(album: Album) {
        albumDao.delete(album)
    }

    override fun getAlbumById(id: Int): Album {
        return albumDao.getAlbumById(id)
    }

    override fun getAllAlbums(): List<Album> {
        return albumDao.getAllAlbums()
    }

    override suspend fun insert(artist: Artist) {
        artistDao.insert(artist)
    }

    override suspend fun update(artist: Artist) {
        artistDao.update(artist)
    }

    override fun delete(artist: Artist) {
        artistDao.delete(artist)
    }

    override fun getArtistById(id: Int): Artist {
        return artistDao.getArtistById(id)
    }

    override fun getAllArtists(): Flow<List<Artist>> {
        return artistDao.getAllArtists()
    }

    override suspend fun insertArtistSongCrossRef(crossRef: SongArtistCrossRef) {
        return artistDao.insertArtistSongCrossRef(crossRef)
    }

    override fun getSongbyArtistID(Id: Int): Flow<ArtistWithSongs>{
        return artistDao.getSongbyArtistID(Id)
    }

//    override fun getArtistWithSongs(): Flow<List<ArtistWithSongs>> {
//        return artistDao.getArtistWithSongs()
//    }

    override suspend fun insert(playlist: Playlist) {
        playlistDao.insert(playlist)
    }

    override suspend fun update(playlist: Playlist) {
        playlistDao.update(playlist)
    }

    override fun delete(playlist: Playlist) {
        playlistDao.delete(playlist)
    }

    override fun getPlaylistById(id: Int): Playlist {
        return playlistDao.getPlaylistById(id)
    }

    override fun getAllPlaylists(): Flow<List<Playlist>> {
        return playlistDao.getAllPlaylists()
    }

    override suspend fun insert(song: Song) {
        songDao.insert(song)
    }

    override suspend fun update(song: Song) {
        songDao.update(song)
    }

    override fun delete(song: Song) {
        songDao.delete(song)
    }

    override fun getSongById(id: Int): Song {
        return songDao.getSongById(id)
    }

    override fun getAllSongs(): List<Song> {
        return songDao.getAllSongs()
    }

    override fun getSongWithArtists(): Flow<List<SongWithArtists>> {
        return songDao.getSongWithArtists()
    }

//    override fun getSongWithArtists(): Flow<List<SongWithArtists>> {
//        return songDao.getSongWithArtists()
//    }


    override suspend fun insert(user: User) {
        userDao.insert(user)
    }

    override suspend fun update(user: User) {
        userDao.update(user)
    }

    override fun delete(user: User) {
        userDao.delete(user)
    }

    override fun getUserById(id: Int): User {
        return userDao.getUserById(id)
    }

    override fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    override fun getUser(username: String, password: String): User {
        return userDao.getUser(username, password)
    }

    override suspend fun insertSongPlaylistCrossRef(crossRef: PlaylistSongCrossRef) {
        playlistSongDao.insertSongPlaylistCrossRef(crossRef)
    }



}