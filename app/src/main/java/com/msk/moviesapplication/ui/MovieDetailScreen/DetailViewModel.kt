package com.msk.moviesapplication.ui.MovieDetailScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.moviesapplication.Pagination.DefaulthPaginator
import com.msk.moviesapplication.Repository.DetailScreen.MoviesDetailRepository
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
class DetailViewModel @Inject constructor(private val MovieDetailRepo:MoviesDetailRepository) :ViewModel(){

    private val _MovieDetailStateflow:MutableStateFlow<MovieDetailState> = MutableStateFlow(
        MovieDetailState()
    )
    val MovieDetailStateflow=_MovieDetailStateflow

    private var getCommentJob: Job?=null

    private var movieId:Int?=null

    private var currentPage=1
    private val paginator=DefaulthPaginator(
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
                currentPage
            }

        }
    )


    fun OnEvent(event: DetailEvent){
        when(event){
            is DetailEvent.getdetails->{
                if (event.movieid!=null) movieId=event.movieid
                getDetails(movieId!!)
            }

            is DetailEvent.getComment->{
                viewModelScope.launch {
                    if (event.movieid!=null) movieId=event.movieid

                    paginator.loadNextItems()

                }
            }
        }
    }



     private fun getDetails( movieId: Int){

        MovieDetailRepo.getDetails(movieID =movieId ).onEach{
            it.onSuccess {
                _MovieDetailStateflow.value=MovieDetailStateflow.value.copy(details = it, isError = "")
            }
            it.onFailure {
                _MovieDetailStateflow.value=MovieDetailStateflow.value.copy(isError =it.localizedMessage ?: "Exeption" )
            }
        }.launchIn(viewModelScope)
    }

    private fun getComments( movieId: Int,page:Int){
        getCommentJob?.cancel()
        getCommentJob= MovieDetailRepo.getcomments(movieid =movieId,page).onEach{
            it.onSuccess {
                val results= MovieDetailStateflow.value.comment?.results ?: listOf()
                _MovieDetailStateflow.value=MovieDetailStateflow.value.copy(comment =it.copy(results = results + it.results  ), isError = "")
                currentPage++
                if (it.totalPages==it.page){
                    _MovieDetailStateflow.value=_MovieDetailStateflow.value.copy(endReached = true)
                }
            }
            it.onFailure {
                _MovieDetailStateflow.value=MovieDetailStateflow.value.copy(isError =it.localizedMessage ?: "Exeption" )
            }


        }.launchIn(viewModelScope)
    }



}