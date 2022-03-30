package com.msk.moviesapplication.Util

import com.msk.moviesapplication.Responces.Data.genre.Genre

sealed class Sorting_Value(val value:String){
    object POPULARITY:Sorting_Value("popularity.desc")
    object RELEASE:Sorting_Value("release.date.desc")
    object REVENUE:Sorting_Value("revenue.desc")
    object ALPHABETICALLY:Sorting_Value("original_title.desc")
    object VOTE_AVARAGE:Sorting_Value("vote_average.desc")
    object VOTE_COUNT:Sorting_Value("vote_count.desc")
}