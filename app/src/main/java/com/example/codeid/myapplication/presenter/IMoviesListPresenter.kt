package com.example.codeid.myapplication.presenter

import com.example.codeid.myapplication.model.MovieModel

interface IMoviesListPresenter {

    var isInternetConnected: Boolean

    fun setupListView()

    fun setupConnectivityListener()

    fun loadListData()

    fun onMovieSelected(movieModel: MovieModel)

    fun getBaseImgPath():String

    fun updateConnectionStatus(isConnection: Boolean)

    fun changeListMode(mode: ListMode)
}