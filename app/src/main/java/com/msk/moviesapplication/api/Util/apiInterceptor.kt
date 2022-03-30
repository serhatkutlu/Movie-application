package com.msk.moviesapplication.api.Util

import com.msk.moviesapplication.Util.Constants.API_KEY
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Interceptor
import okhttp3.Response


class apiInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
        val url=request.url.newBuilder().addQueryParameter("api_key",API_KEY).build()
        val newRequest=request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}