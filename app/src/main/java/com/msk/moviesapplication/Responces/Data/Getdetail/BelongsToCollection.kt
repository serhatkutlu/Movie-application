package com.msk.moviesapplication.Responces.Data.Getdetail


import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: String, // /AvnqpRwlEaYNVL6wzC4RN94EdSd.jpg
    @SerializedName("id")
    val id: Int, // 531241
    @SerializedName("name")
    val name: String, // Spider-Man (Avengers) Collection
    @SerializedName("poster_path")
    val posterPath: String // /nogV4th2P5QWYvQIMiWHj4CFLU9.jpg
)