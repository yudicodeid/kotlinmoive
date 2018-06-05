package com.example.codeid.myapplication.api

interface IMovieApiListener {

    fun onListDataPop(list: List<MovieApiModel>, requestType: RequestType)

}