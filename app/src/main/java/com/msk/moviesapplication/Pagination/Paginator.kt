package com.msk.moviesapplication.Pagination

interface Paginator {
    suspend fun loadNextItems()
    fun reset()
}