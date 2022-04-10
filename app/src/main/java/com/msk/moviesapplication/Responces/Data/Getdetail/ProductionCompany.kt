package com.msk.moviesapplication.Responces.Data.Getdetail


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("id")
    val id: Int, // 420
    @SerializedName("logo_path")
    val logoPath: String, // /hUzeosd33nzE5MCNsZxCGEKTXaQ.png
    @SerializedName("name")
    val name: String, // Marvel Studios
    @SerializedName("origin_country")
    val originCountry: String // US
)