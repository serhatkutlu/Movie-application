package com.msk.moviesapplication.ui.MovieDetailScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.moviesapplication.Pagination.DefaulthPaginator
import com.msk.moviesapplication.Repository.DetailScreen.MoviesDetailRepositoryImp
import com.msk.moviesapplication.Repository.MovieScreen.MovieRepositoryImp
import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.Responces.Data.comments.Result
import com.msk.moviesapplication.Responces.Data.comments.comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val MovieDetailRepo:MoviesDetailRepositoryImp) :ViewModel(){

    private val _MovieDetailStateflow:MutableStateFlow<MovieDetailState> = MutableStateFlow(
        MovieDetailState()
    )
    val MovieDetailStateflow=_MovieDetailStateflow

    private var getCommentJob: Job?=null

    var movieId:Int?=null

    val paginator=DefaulthPaginator(
        initialKey = 1,
        onRequest = {
            if (getCommentJob?.isActive ?: false){

                return@DefaulthPaginator it
            }
            if (movieId==null){
                it
            }
            else{
                getComments(movieId = movieId!!, page = it)
                it+1
            }

        }
    )


    fun OnEvent(event: DetailEvent){
        when(event){
            is DetailEvent.getdetails->{
                movieId?.let {
                    getDetails(movieId!!)
                    Log.d("hatalar","detail")

                }
            }

            is DetailEvent.getComment->{
                viewModelScope.launch {
                    Log.d("hatalar","comments")
                    paginator.loadNextItems()

                }
            }
        }
    }



     private fun getDetails( movieId: Int){

        MovieDetailRepo.getDetails(movieID =movieId ).onEach{
            _MovieDetailStateflow.value=MovieDetailStateflow.value.copy(details = it)
        }.launchIn(viewModelScope)
    }

    private fun getComments( movieId: Int,page:Int){
        getCommentJob?.cancel()
        _MovieDetailStateflow.value=MovieDetailStateflow.value.copy(isLoading = true)

        getCommentJob= MovieDetailRepo.getcomments(movieid =movieId,page).onEach{

            val results= MovieDetailStateflow.value.comment?.results ?: listOf()
            _MovieDetailStateflow.value=MovieDetailStateflow.value.copy(comment =it.copy(results = results + it.results  ))
            _MovieDetailStateflow.value=MovieDetailStateflow.value.copy(isLoading = false)

            if (it.totalPages==it.page){
                Log.d("hatalar","Finish")
                _MovieDetailStateflow.value=_MovieDetailStateflow.value.copy(endReached = true)
            }
        }.launchIn(viewModelScope)
    }



}