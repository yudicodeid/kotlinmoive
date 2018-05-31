package com.example.codeid.myapplication.api


interface IMovieApi {

    fun getPopularList(page: Int)

    fun getTopRated(page: Int)

    fun setMovieApiListener(listener: IMovieApiListener)

}

