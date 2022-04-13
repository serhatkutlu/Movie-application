package com.msk.moviesapplication.Responces.Data.Getdetail


import com.google.gson.annotations.SerializedName
import com.msk.moviesapplication.Responces.Data.genre.Genre

data class Details(
    @SerializedName("adult")
    val adult: Boolean?, // false
    @SerializedName("backdrop_path")
    val backdropPath: String?, // /iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection?,
    @SerializedName("budget")
    val budget: Int, // 200000000
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("homepage")
    val homepage: String, // https://www.spidermannowayhome.movie
    @SerializedName("id")
    val id: Int, // 634649
    @SerializedName("imdb_id")
    val imdbİd: String, // tt10872600
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String?, // Spider-Man: No Way Home
    @SerializedName("overview")
    val overview: String?, // Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.
    @SerializedName("popularity")
    val popularity: Double, // 5786.583
    @SerializedName("poster_path")
    val posterPath: String?, // /1g0dhYtq4irTY1GPXvft6k4YLjm.jpg
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate: String?, // 2021-12-15
    @SerializedName("revenue")
    val revenue: Double?, // 1888000000
    @SerializedName("runtime")
    val runtime: Int?, // 148
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String, // Released
    @SerializedName("tagline")
    val tagline: String?, // The Multiverse unleashed.
    @SerializedName("title")
    val title: String?, // Spider-Man: No Way Home
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double?, // 8.2
    @SerializedName("vote_count")
    val voteCount: Int // 11176
)