package com.example.dacs3.viewModel

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dacs3.MyApp

object AppViewModelProvider {
    val Factory= viewModelFactory {
        initializer {
            UserViewModel(
                myApp().container.musicRepository
            )
        }

        initializer {
            HomeViewModel(
                this.createSavedStateHandle(),
                myApp().container.musicRepository
            )
        }

        initializer {
            ArtistDetailsViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                musicRepository = myApp().container.musicRepository
            )
        }

        initializer {
            SongDetailsViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                songsRepository = myApp().container.musicRepository
            )
        }

    }
}

fun CreationExtras.myApp(): MyApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApp)