package com.msk.moviesapplication.Responces.Data.Getdetail


import com.google.gson.annotations.SerializedName
import com.msk.moviesapplication.Responces.Data.genre.Genre

data class Details(
    @SerializedName("adult")
    val adult: Boolean?=null, // false
    @SerializedName("backdrop_path")
    val backdropPath: String?=null, // /iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection?=null,
    @SerializedName("budget")
    val budget: Int?=null, // 200000000
    @SerializedName("genres")
    val genres: List<Genre>?=null,
    @SerializedName("homepage")
    val homepage: String?=null, // https://www.spidermannowayhome.movie
    @SerializedName("id")
    val id: Int, // 634649
    @SerializedName("imdb_id")
    val imdbİd: String?=null, // tt10872600
    @SerializedName("original_language")
    val originalLanguage: String?=null, // en
    @SerializedName("original_title")
    val originalTitle: String?=null, // Spider-Man: No Way Home
    @SerializedName("overview")
    val overview: String?=null, // Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.
    @SerializedName("popularity")
    val popularity: Double?=null, // 5786.583
    @SerializedName("poster_path")
    val posterPath: String?=null, // /1g0dhYtq4irTY1GPXvft6k4YLjm.jpg
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>?=null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>?=null,
    @SerializedName("release_date")
    val releaseDate: String?=null, // 2021-12-15
    @SerializedName("revenue")
    val revenue: Double?=null, // 1888000000
    @SerializedName("runtime")
    val runtime: Int?=null, // 148
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>?=null,
    @SerializedName("status")
    val status: String?=null, // Released
    @SerializedName("tagline")
    val tagline: String?=null, // The Multiverse unleashed.
    @SerializedName("title")
    val title: String?=null, // Spider-Man: No Way Home
    @SerializedName("video")
    val video: Boolean?=null, // false
    @SerializedName("vote_average")
    val voteAverage: Double?=null, // 8.2
    @SerializedName("vote_count")
    val voteCount: Int?=null// 11176
)