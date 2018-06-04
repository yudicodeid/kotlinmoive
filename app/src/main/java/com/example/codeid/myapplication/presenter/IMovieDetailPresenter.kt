package com.example.codeid.myapplication.presenter

import com.example.codeid.myapplication.model.MovieModel

interface IMovieDetailPresenter {

    fun loadMovieData(movieModel: MovieModel)

    fun getBaseImgPath(): String

}