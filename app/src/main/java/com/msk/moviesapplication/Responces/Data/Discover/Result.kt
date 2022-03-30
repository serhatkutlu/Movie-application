package com.msk.moviesapplication.Responces.Data.Discover


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: Any, // null
    @SerializedName("genre_ids")
    val genreİds: List<Int>,
    @SerializedName("id")
    val id: Int, // 955915
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Closing the Loop
    @SerializedName("overview")
    val overview: String, // A documentary about the circular economy revolution
    @SerializedName("popularity")
    val popularity: Int, // 0
    @SerializedName("poster_path")
    val posterPath: Any, // null
    @SerializedName("release_date")
    val releaseDate: String, // 2022-04-22
    @SerializedName("title")
    val title: String, // Closing the Loop
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Int, // 0
    @SerializedName("vote_count")
    val voteCount: Int // 0
)