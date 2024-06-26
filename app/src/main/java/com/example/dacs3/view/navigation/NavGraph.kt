package com.example.dacs3.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dacs3.view.artistDetails.ArtistScreen
import com.example.dacs3.view.home.HomeScreen
import com.example.dacs3.view.songDetails.SongDetailsScreen
import com.example.dacs3.view.songDetails.songScreen
import com.example.dacs3.view.user.LoginScreen1
import com.example.dacs3.view.user.LoginScreen2
import com.example.dacs3.view.user.RegisterScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = LoginScreen1.route, modifier = Modifier
    ) {
        composable(route = LoginScreen1.route) {
            LoginScreen1(
                loginScreen2 = { navController.navigate(LoginScreen2.route) },
                registerScreen = { navController.navigate(RegisterScreen.route) }
            )
        }

        composable(route = LoginScreen2.route) {
            LoginScreen2(
                homeScreen = { navController.navigate(HomeScreen.route) }
            )
        }
        composable(route = RegisterScreen.route) {
            RegisterScreen(checkRegister = { true })
        }

        composable(
            route = HomeScreen.route,
            arguments = listOf(
                navArgument(HomeScreen.user_id) {
                    type = NavType.IntType
                }
            )
        ) {
            HomeScreen(
                goToDetailArtist = { navController.navigate("${ArtistScreen.route}/${it}") },
                goToSongDetails = { navController.navigate("${songScreen.route}/${it}") }
            )
        }

        composable(
            route = ArtistScreen.routeWithId,
            arguments = listOf(
                navArgument(ArtistScreen.artist_id) {
                    type = NavType.IntType
                }
            )) {
            ArtistScreen()
        }
        composable(
            route = songScreen.routeWithId,
            arguments = listOf(
                navArgument(songScreen.song_id) {
                    type = NavType.IntType
                }
            )
        ) {
            SongDetailsScreen(
                goBackEvent = {},
                goOptionEvent = {},
                goShareEvent = {}
            )
        }
    }
}