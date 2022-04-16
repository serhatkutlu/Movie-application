package com.msk.moviesapplication.ui.MovieDetailScreen

import com.google.common.truth.Truth
import com.msk.moviesapplication.CoroutineTestRule
import com.msk.moviesapplication.Repository.DetailScreen.FakeMoviesDetailRepositoryImp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest{

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    lateinit var viewModel: DetailViewModel

    lateinit var repository:FakeMoviesDetailRepositoryImp

    @Before
    fun setup(){
        repository= FakeMoviesDetailRepositoryImp()
        viewModel= DetailViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun`get detail func  test,return movie id`() = coroutineTestRule.runBlockingTest{

        viewModel.OnEvent(DetailEvent.getdetails(1))

        val details=viewModel.MovieDetailStateflow.first()
        Truth.assertThat(details.details?.id).isEqualTo(1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun`page and endReached test ,return page and value and  false`() = coroutineTestRule.runBlockingTest{
        repeat(2){
            viewModel.OnEvent(DetailEvent.getComment(1))
        }
        val details=viewModel.MovieDetailStateflow.first()
        Truth.assertThat(details.comment?.page).isEqualTo(2)
        Truth.assertThat(details.endReached).isEqualTo(true)

    }

}