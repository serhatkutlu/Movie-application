package com.msk.moviesapplication.Pagination

import android.util.Log
import com.msk.moviesapplication.Responces.Data.Discover.Movies


class DefaulthPaginator(
    private val initialKey: Int,
    private inline val onRequest: suspend (nextKey: Int) -> Int,

): Paginator {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) {
            return
        }
        Log.d("hatalar",currentKey.toString())
        isMakingRequest = true
        currentKey = onRequest(currentKey)

        isMakingRequest = false


    }

    override fun reset() {
        currentKey = initialKey
    }
}