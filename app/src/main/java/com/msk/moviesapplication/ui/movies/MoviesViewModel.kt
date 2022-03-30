package com.msk.moviesapplication.ui.movies

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.moviesapplication.Repository.MovieRepositoryImp
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(val MovieRepository:MovieRepositoryImp):ViewModel() {

    private val _MoviesState:MutableState<MoviesState> = mutableStateOf(MoviesState())
    val MoviesState:State<MoviesState> =_MoviesState

    var Genres:genres?=null

    private var getMovieJob : Job? =null
    private var getGenreJob : Job? =null

    init {
        GetGenre()
        GetMovies(Sorting_Value.POPULARITY)

    }
    fun OnEvent(MoviesEvent:MoviesEvent){
        when(MoviesEvent){
            is MoviesEvent.OrderSection->{
                if (MoviesState.value.sorting==MoviesEvent.sortingValue&&MoviesState.value.genre?.id==MoviesEvent.genre?.id){
                    GetMovies(MoviesEvent.sortingValue,MoviesEvent.genre)
                }
            }
            is MoviesEvent.ToggleOrderSection->{
                _MoviesState.value.isOrderSectionVisible=!MoviesState.value.isOrderSectionVisible
            }
            is MoviesEvent.openDetailScreen->{

            }
        }
    }

    private fun GetMovies( sortingValue: Sorting_Value,  genre: Genre?=null){
        getMovieJob?.cancel()

        getMovieJob=MovieRepository.getMovies(sortingValue,genre).onEach {
            _MoviesState.value=MoviesState.value.copy(movies = it)
        }.launchIn(viewModelScope)
    }

    private fun GetGenre(){
        getGenreJob?.cancel()

        getGenreJob=MovieRepository.getGenres().onEach {
            Genres=it
        }.launchIn(viewModelScope)
    }

}