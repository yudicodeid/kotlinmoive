package com.example.codeid.myapplication.api

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface IMovieApi {

    fun getPopularList(page: Int)

    fun getTopRated(page: Int)

}

class MovieApiFactory {

    companion object {

        fun createApi(): IMovieApi {
            return TheMovieDbOrgApi()
        }

    }
}

