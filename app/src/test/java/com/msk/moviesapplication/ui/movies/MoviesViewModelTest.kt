package com.msk.moviesapplication.ui.movies

import com.msk.moviesapplication.Repository.MovieScreen.MovieRepositoryImpFake
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import com.msk.moviesapplication.CoroutineTestRule
import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Responces.Data.genre.genres
import com.msk.moviesapplication.Util.Sorting_Value
import com.msk.moviesapplication.Util.Sorting_data
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest{

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    lateinit var viewModel:MoviesViewModel
    lateinit var repositoryImpFake:MovieRepositoryImpFake
    @Before
    fun setup(){
        repositoryImpFake= MovieRepositoryImpFake()
        viewModel= MoviesViewModel(repositoryImpFake)

    }

    /**
      Order Section
     */

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Set different Sorting  value ,return changed sorting value`()=coroutineTestRule.runBlockingTest {
        viewModel.OnEvent(MoviesEvent.OrderSection(sortingData = Sorting_data(Sorting_value = Sorting_Value.VOTE_AVARAGE, Genre =viewModel.SortingData.value.Genre )))
        val sortingValue=viewModel.SortingData.first().Sorting_value
        assertThat(sortingValue).isEqualTo(Sorting_Value.VOTE_AVARAGE)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Set same Sorting  value ,return changed sorting value and same genre list`()=coroutineTestRule.runBlockingTest {
        viewModel.OnEvent(MoviesEvent.OrderSection(sortingData = Sorting_data(Sorting_value = viewModel.SortingData.value.Sorting_value, Genre = mutableListOf(Genre(1,"action")))))
        viewModel.OnEvent(MoviesEvent.OrderSection(sortingData = Sorting_data(Sorting_value = Sorting_Value.POPULARITY, Genre = mutableListOf() )))
        val sortingValue=viewModel.SortingData.first()
        assertThat(sortingValue.Sorting_value).isEqualTo(Sorting_Value.POPULARITY)
        assertThat(sortingValue.Genre).isEqualTo(mutableListOf(Genre(1,"action")))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `If the same type is added, it will be deleted from the list,return selected genre list`()=coroutineTestRule.runBlockingTest {
        viewModel.OnEvent(MoviesEvent.OrderSection(sortingData = Sorting_data(Sorting_value = viewModel.SortingData.value.Sorting_value, Genre = mutableListOf(Genre(1,"action")) )))
        viewModel.OnEvent(MoviesEvent.OrderSection(sortingData = Sorting_data(Sorting_value = viewModel.SortingData.value.Sorting_value, Genre = mutableListOf(Genre(2,"musical")) )))
        viewModel.OnEvent(MoviesEvent.OrderSection(sortingData = Sorting_data(Sorting_value = viewModel.SortingData.value.Sorting_value, Genre = mutableListOf(Genre(1,"action")) )))

        val sortingValue=viewModel.SortingData.first()
        assertThat(sortingValue.Genre).isEqualTo(mutableListOf( Genre(2,"musical")))
    }

    /**
    Toggle Order Section
     */

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when clicked isOrderSectionVisible value changed ,return inverse value`()=coroutineTestRule.runBlockingTest {
        val value=viewModel.MoviesState.value.isOrderSectionVisible
        viewModel.OnEvent(MoviesEvent.ToggleOrderSection)
        val MoviesState=viewModel.MoviesState.first().isOrderSectionVisible
        assertThat(MoviesState).isEqualTo(!value)
    }

    /**
    loadNextPage
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `pagination testing ,return next page`()=coroutineTestRule.runBlockingTest {
        val page=4
        repeat(page){
            viewModel.OnEvent(MoviesEvent = MoviesEvent.LoadNextPage)

        }
        val movies=viewModel.MoviesState.first().movies
        assertThat(movies?.page).isEqualTo(page)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `endReached testing,return true`()=coroutineTestRule.runBlockingTest {
        val page=5
        repeat(page){
            viewModel.OnEvent(MoviesEvent = MoviesEvent.LoadNextPage)

        }
        val movies=viewModel.MoviesState.first()
        assertThat(movies.endReached).isEqualTo(true)
    }






}