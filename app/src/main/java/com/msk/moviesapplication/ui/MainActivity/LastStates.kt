package com.msk.moviesapplication.ui.MainActivity

import com.msk.moviesapplication.Responces.Data.Discover.Movies
import com.msk.moviesapplication.Util.Sorting_data
import com.msk.moviesapplication.ui.movies.MoviesState

data class LastStates(val moviesState: MoviesState,val sortingData: Sorting_data  )
