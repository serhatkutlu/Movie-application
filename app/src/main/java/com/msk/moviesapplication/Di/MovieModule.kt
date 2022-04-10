package com.msk.moviesapplication.Di

import com.msk.moviesapplication.Util.Constants.URL
import com.msk.moviesapplication.api.Util.apiInterceptor
import com.msk.moviesapplication.api.MovieApi
import com.msk.moviesapplication.api.MovieDetailApi
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
object MovieModule {


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
    fun provideRetrofit(okHttpClient:OkHttpClient): Retrofit {

         return Retrofit.Builder()
             .baseUrl(URL)
             .client(okHttpClient)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
    }
    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit):MovieApi{
        return retrofit.create(MovieApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMovieDetailApi(retrofit: Retrofit):MovieDetailApi{
        return retrofit.create(MovieDetailApi::class.java)
    }
}