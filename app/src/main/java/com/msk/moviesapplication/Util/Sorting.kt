package com.msk.moviesapplication.Util

import com.msk.moviesapplication.Responces.Data.genre.Genre
import com.msk.moviesapplication.Responces.Data.genre.genres
import java.lang.StringBuilder

sealed class Sorting_Value(val value:String){
    object POPULARITY:Sorting_Value("popularity.desc")
    object RELEASE:Sorting_Value("release_date.desc")
    object REVENUE:Sorting_Value("revenue.desc")
    object ALPHABETICALLY:Sorting_Value("original_title.desc")
    object VOTE_AVARAGE:Sorting_Value("vote_average.desc")
    object VOTE_COUNT:Sorting_Value("vote_count.desc")
}


data class Sorting_data(
    val Sorting_value:Sorting_Value,
    val Genre: MutableList<Genre>
){

    fun GenreToString():String{
        if (Genre.isNotEmpty()){
            val sublist= mutableListOf<String>()
            Genre.forEach{
                sublist.add(it.id.toString())
            }
            return  sublist.joinToString()
        }
        else return ""
    }
}


