package com.msk.moviesapplication.Responces.Data.genre


import com.google.gson.annotations.SerializedName

data class genres(
    @SerializedName("genres")
    val genres: MutableList<Genre>
)