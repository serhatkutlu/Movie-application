package com.msk.moviesapplication.Responces.Data.Discover


import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: MutableList<Result>,
    @SerializedName("total_pages")
    val totalPages: Int, // 33005
    @SerializedName("total_results")
    val totalResults: Int // 660095
)