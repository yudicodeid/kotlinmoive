package com.example.codeid.myapplication.api

import android.database.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface IRetrofitTheMovieDbOrgApi {

    @GET("/movie/popular")
    fun getPopularList(@Query("api_key") apiKey: String,
                       @Query("language") language: String="en-US",
                       @Query("page") page: Int = 1,
                       @Query("region") region: String="") : Observable<TheMovieDbResult>


    @GET("/movie/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String,
                       @Query("language") language: String="en-US",
                       @Query("page") page: Int = 1,
                       @Query("region") region: String="") : Observable<TheMovieDbResult>

}

class RetrofitTheMovieDbOrgApiWrapper {

    companion object {

        fun createApi() : IRetrofitTheMovieDbOrgApi {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3")
                    .build()

            return retrofit.create(IRetrofitTheMovieDbOrgApi::class.java)
        }
    }

}