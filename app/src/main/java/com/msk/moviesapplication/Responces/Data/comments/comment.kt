package com.msk.moviesapplication.Responces.Data.comments


import com.google.gson.annotations.SerializedName

data class comment(
    @SerializedName("id")
    val id: Int, // 634649
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int, // 1
    @SerializedName("total_results")
    val totalResults: Int // 8
)