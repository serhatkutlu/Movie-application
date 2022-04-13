package com.msk.moviesapplication.Util

fun String.addbaseUrl():String{
    return Constants.IMAGEBASEURl+this
}

fun String.ChangeAvatarImage():String{
    var url=this
    if (url.length<45){
        url=url.addbaseUrl()
    }
    else{
        url=url.drop(1)
    }
    return url
}