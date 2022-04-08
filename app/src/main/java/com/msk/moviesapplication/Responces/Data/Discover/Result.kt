package com.msk.moviesapplication.Responces.Data.Discover


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg
    @SerializedName("genre_ids")
    val genreids: List<Int>,
    @SerializedName("id")
    val id: Int, // 634649
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Spider-Man: No Way Home
    @SerializedName("overview")
    val overview: String, // Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.
    @SerializedName("popularity")
    val popularity: Float, // 6646
    @SerializedName("poster_path")
    val posterPath: String?, // /1g0dhYtq4irTY1GPXvft6k4YLjm.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2021-12-15
    @SerializedName("title")
    val title: String, // Spider-Man: No Way Home
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 8.2
    @SerializedName("vote_count")
    val voteCount: Int // 10971
)