package com.msk.moviesapplication.Responces.Data.Discover


import com.google.gson.annotations.SerializedName

data class movies(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int, // 32919
    @SerializedName("total_results")
    val totalResults: Int // 658369
)