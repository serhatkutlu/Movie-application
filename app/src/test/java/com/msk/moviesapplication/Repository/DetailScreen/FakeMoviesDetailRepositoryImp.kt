package com.msk.moviesapplication.Repository.DetailScreen

import com.msk.moviesapplication.Responces.Data.Getdetail.Details
import com.msk.moviesapplication.Responces.Data.comments.comment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*

class FakeMoviesDetailRepositoryImp:MoviesDetailRepository {
    val details=Details(id=1)
    val comment=comment(id = 1, page = 1, results = listOf(), totalPages = 2, totalResults =40 )
    override fun getDetails(movieID: Int): Flow<Result<Details>> {
        return flow {
            emit(Result.success(details.copy(id = movieID)))
        }
    }

    override fun getcomments(movieid: Int, page: Int): Flow<Result<comment>> {
        return flow {
            emit(Result.success(comment.copy(id = movieid, page =page )))
        }
    }
}