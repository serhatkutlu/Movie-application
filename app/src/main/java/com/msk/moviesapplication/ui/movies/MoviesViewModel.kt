package com.msk.moviesapplication.ui.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.moviesapplication.Pagination.DefaulthPaginator
import com.msk.moviesapplication.Repository.MovieScreen.MovieRepository
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.ui.MainActivity.LastStates
import com.msk.moviesapplication.ui.Util.MoviesScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val MovieRepository: MovieRepository):ViewModel() {

    private val _SortingData:MutableStateFlow<Sorting_data> = MutableStateFlow(Sorting_data(Sorting_Value.POPULARITY,mutableListOf()))
    val SortingData:StateFlow<Sorting_data> = _SortingData



    private val _MoviesState= MutableStateFlow(MoviesState())
    val MoviesState:StateFlow<MoviesState> =_MoviesState


    val _AllGenres:MutableStateFlow<genres> = MutableStateFlow(genres(mutableListOf()))
    val AllGenres:StateFlow<genres> =_AllGenres

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
                    if (MoviesEvent.sortingData.Genre.isEmpty()) {
                        return

                    }

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
                val navController=MoviesEvent.navController
                val movieid=MoviesEvent.movieId
                navController.navigate(MoviesScreenRoute.MoviesDetail.route+"?movieid=$movieid")

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
                if (it.totalPages==page){
                    _MoviesState.value=MoviesState.value.copy(endReached = true)
                }

            }
            it.onFailure {
                _MoviesState.value=MoviesState.value.copy(error = it.localizedMessage ?: "Exeption", isLoading = false)
            }


        }.launchIn(viewModelScope)



    }

    private fun GetGenre(){
        getGenreJob?.cancel()

        getGenreJob=MovieRepository.getGenres().onEach {
            it.onSuccess {
                _AllGenres.value=it
            }


        }.launchIn(viewModelScope)
    }

    private fun resetmovies(){
        _MoviesState.value=_MoviesState.value.copy(movies = null)
        paginator.reset()
        currentPage=1
        loadNextItems()
         if (AllGenres.value.genres.isEmpty()){
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