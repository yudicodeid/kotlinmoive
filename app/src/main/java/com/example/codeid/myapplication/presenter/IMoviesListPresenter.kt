package com.example.codeid.myapplication.presenter

import com.example.codeid.myapplication.model.MovieModel

interface IMoviesListPresenter {

    fun loadPopular()

    fun loadTopRated()

    fun onMovieSelected(movieModel: MovieModel)

    fun getBaseImgPath():String

}