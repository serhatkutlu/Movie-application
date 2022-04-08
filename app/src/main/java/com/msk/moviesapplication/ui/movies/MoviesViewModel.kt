package com.msk.moviesapplication.ui.movies

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.msk.moviesapplication.Pagination.DefaulthPaginator
import com.msk.moviesapplication.Repository.MovieRepositoryImp
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.api.api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import com.msk.moviesapplication.Responces.Data.Discover.Result
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(val MovieRepository:MovieRepositoryImp,val api: api):ViewModel() {



    //private val _SortingState:MutableState<Sorting_data> = mutableStateOf(Sorting_data(Sorting_Value.POPULARITY,genres(listOf())))


    private val _SortingData:MutableStateFlow<Sorting_data> = MutableStateFlow(Sorting_data(Sorting_Value.POPULARITY,mutableListOf()))
    val SortingData:StateFlow<Sorting_data> = _SortingData



    private val _MoviesState= mutableStateOf(MoviesState())
    val MoviesState:State<MoviesState> =_MoviesState

    //val isPaginationLoading:MutableState<Boolean> = mutableStateOf(false)

    val Genres:MutableState<genres> = mutableStateOf(genres(mutableListOf()))

    private var getMovieJob : Job? =null
    private var getGenreJob : Job? =null

    private val paginator = DefaulthPaginator(
        initialKey = 1,
        onLoadUpdated = {
            _MoviesState.value.isLoading=it
        },
        onRequest = { nextPage ->

            GetMovies(sortingValue = SortingData.value,nextPage)
            nextPage+1
        }


    )

    init {
        GetGenre()
    }




    fun OnEvent(MoviesEvent:MoviesEvent){
        when(MoviesEvent){
            is MoviesEvent.OrderSection->{
                if (SortingData.value.Sorting_value==MoviesEvent.sortingData.Sorting_value){
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
        }
    }

    private fun GetMovies( sortingValue: Sorting_data,page:Int=1){
        getMovieJob?.cancel()

        getMovieJob=MovieRepository.getMovies(sortingValue,page).onEach {

            val movies=MoviesState.value.movies?.results ?: listOf()
            //_MoviesState.value.movies=it.copy(results = movies+it.results)
            _MoviesState.value=MoviesState.value.copy(movies =it.copy(results = movies+it.results) )
            if (it.totalPages==page+1){
                _MoviesState.value.endReached=true
            }
        }.launchIn(viewModelScope)
    }

    private fun GetGenre(){
        getGenreJob?.cancel()

        getGenreJob=MovieRepository.getGenres().onEach {
            Genres.value=it
        }.launchIn(viewModelScope)
    }

     fun resetPagination(){
        paginator.reset()
    }

    private fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}