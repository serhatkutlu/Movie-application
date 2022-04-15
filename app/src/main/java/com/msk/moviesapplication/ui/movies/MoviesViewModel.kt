package com.msk.moviesapplication.ui.movies

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.moviesapplication.Pagination.DefaulthPaginator
import com.msk.moviesapplication.Repository.MovieScreen.MovieRepositoryImp
import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.api.MovieApi
import com.msk.moviesapplication.ui.MainActivity.LastStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(val MovieRepository: MovieRepositoryImp):ViewModel() {





    private val _SortingData:MutableStateFlow<Sorting_data> = MutableStateFlow(Sorting_data(Sorting_Value.POPULARITY,mutableListOf()))
    val SortingData:StateFlow<Sorting_data> = _SortingData



    private val _MoviesState= mutableStateOf(MoviesState())
    val MoviesState:State<MoviesState> =_MoviesState


    val Genres:MutableState<genres> = mutableStateOf(genres(mutableListOf()))

    private var getMovieJob : Job? =null
    private var getGenreJob : Job? =null

    private var initialPageKey=1
    private var currentPage=1
    private val paginator :DefaulthPaginator by lazy{
        DefaulthPaginator(
            initialKey = initialPageKey,
            onRequest = { nextPage ->
                if (getMovieJob?.isActive ?: false){
                    return@DefaulthPaginator nextPage
                }
                GetMovies(sortingValue = SortingData.value,nextPage)
                currentPage
            }
        )
    }

    fun OnEvent(MoviesEvent:MoviesEvent){
        when(MoviesEvent){
            is MoviesEvent.OrderSection->{
                if (SortingData.value.Sorting_value==MoviesEvent.sortingData.Sorting_value){
                    if (MoviesEvent.sortingData.Genre.isEmpty()) return

                    val genre=MoviesEvent.sortingData.Genre[0]

                    if(SortingData.value.Genre.contains(genre))  {
                        val fakeList= mutableListOf<Genre>()
                        SortingData.value.Genre.forEach{
                            fakeList.add(it)
                        }
                        fakeList.remove(genre)
                        _SortingData.value=SortingData.value.copy(Genre = fakeList)

                    }
                    else{
                        val fakeList= mutableListOf<Genre>()
                        SortingData.value.Genre.forEach{
                            fakeList.add(it)
                        }
                        fakeList.add(genre)
                        _SortingData.value=SortingData.value.copy(Genre = fakeList)

                    }
                }
                else{
                    _SortingData.value=SortingData.value.copy(Sorting_value = MoviesEvent.sortingData.Sorting_value)
                }
            }
            is MoviesEvent.ToggleOrderSection->{
                _MoviesState.value=MoviesState.value.copy(isOrderSectionVisible = !MoviesState.value.isOrderSectionVisible)
            }
            is MoviesEvent.openDetailScreen->{

            }
            is MoviesEvent.LoadNextPage->{
                loadNextItems()
            }
            is MoviesEvent.AddLastMovies->{
                addLastMovies(MoviesEvent.lastStates)
            }
            is MoviesEvent.resetMovies->{
                resetmovies()
            }
        }
    }

    private fun GetMovies( sortingValue: Sorting_data,page:Int=1){
        getMovieJob?.cancel()
        _MoviesState.value=MoviesState.value.copy(isLoading = true)

        getMovieJob=MovieRepository.getMovies(sortingValue,page).onEach {
            it.onSuccess {
                val movies=MoviesState.value.movies?.results ?: mutableListOf()
                _MoviesState.value=MoviesState.value.copy(movies =it.copy(results = (movies+it.results).toMutableList()) )
                _MoviesState.value=MoviesState.value.copy(isLoading = false, error = "")
                currentPage++
                if (it.totalPages==page+1){
                    _MoviesState.value=MoviesState.value.copy(endReached = true)
                }

            }
            it.onFailure {
                Log.d("hatalar",it.localizedMessage)
                _MoviesState.value=MoviesState.value.copy(error = it.localizedMessage ?: "Exeption", isLoading = false)
            }


        }.launchIn(viewModelScope)



    }

    private fun GetGenre(){
        getGenreJob?.cancel()

        getGenreJob=MovieRepository.getGenres().onEach {
            it.onSuccess {
                Genres.value=it
            }


        }.launchIn(viewModelScope)
    }

    private fun resetmovies(){
         _MoviesState.value=_MoviesState.value.copy(movies = null)
         paginator.reset()
         loadNextItems()
         if (Genres.value.genres.isEmpty()){
             GetGenre()
         }
    }

   private fun addLastMovies(lastStates: LastStates) {
        _SortingData.value=lastStates.sortingData
        _MoviesState.value=lastStates.moviesState
        initialPageKey=lastStates.moviesState.movies?.page ?: 1
    }

    private fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}