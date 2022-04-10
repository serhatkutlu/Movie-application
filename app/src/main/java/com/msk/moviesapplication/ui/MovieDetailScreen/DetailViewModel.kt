package com.msk.moviesapplication.ui.MovieDetailScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.moviesapplication.Repository.DetailScreen.MoviesDetailRepositoryImp
import com.msk.moviesapplication.Repository.MovieScreen.MovieRepositoryImp
import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val MovieDetailRepo:MoviesDetailRepositoryImp) :ViewModel(){

    private val _MovieDetailStateflow:MutableStateFlow<Details?> = MutableStateFlow(null)
    val MovieDetailStateflow=_MovieDetailStateflow



     fun getDetails( movieId: Int){

        MovieDetailRepo.getDetails(movieID =movieId ).onEach{
            _MovieDetailStateflow.value=it
        }.launchIn(viewModelScope)
    }

}