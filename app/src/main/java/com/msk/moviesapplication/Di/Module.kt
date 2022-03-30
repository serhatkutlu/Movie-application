package com.msk.moviesapplication.Di

import com.msk.moviesapplication.Util.Constants.URL
import com.msk.moviesapplication.api.Util.apiInterceptor
import com.msk.moviesapplication.api.api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {


    @Provides
    @Singleton
    fun provideApiInterceptor():Interceptor{
        return apiInterceptor()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor:Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient:OkHttpClient):api{

         return Retrofit.Builder()
             .baseUrl(URL)
             .client(okHttpClient)
             .addConverterFactory(GsonConverterFactory.create())
             .build().create(api::class.java)

    }
}