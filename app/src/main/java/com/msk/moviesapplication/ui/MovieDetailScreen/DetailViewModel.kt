package com.msk.moviesapplication.ui.MovieDetailScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msk.moviesapplication.Pagination.DefaulthPaginator
import com.msk.moviesapplication.Repository.DetailScreen.MoviesDetailRepositoryImp
import com.msk.moviesapplication.Repository.MovieScreen.MovieRepositoryImp
import com.msk.moviesapplication.Responces.Data.Getdetail.Details
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

    private var getCommentJob: Job=Job()

    var movieId:Int?=null

    val paginator=DefaulthPaginator(
        initialKey = 0,
        onRequest = {
            if (getCommentJob.isActive){
                return@DefaulthPaginator it
            }
            getComments(movieId = movieId ?: 3, page = it)
            it+1

        }
    )


    fun OnEvent(event: DetailEvent){
        when(event){
            is DetailEvent.getdetails->{
                movieId?.let {
                    getDetails(movieId!!)
                }
            }

            is DetailEvent.getComment->{
                viewModelScope.launch {

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
        getCommentJob= MovieDetailRepo.getcomments(movieid =movieId,page).onEach{
            MovieDetailStateflow.value.comment.add(it)
            val comments=MovieDetailStateflow.value.comment
            _MovieDetailStateflow.value=MovieDetailStateflow.value.copy(comment =comments )
            if (it.totalPages>it.page+1){
                _MovieDetailStateflow.value=_MovieDetailStateflow.value.copy(endReached = true)
            }
        }.launchIn(viewModelScope)
    }



}