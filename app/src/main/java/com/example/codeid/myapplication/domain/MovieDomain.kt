package com.example.codeid.myapplication.domain

import android.graphics.Movie
import com.example.codeid.myapplication.api.IMovieApi
import com.example.codeid.myapplication.api.MovieApiFactory
import com.example.codeid.myapplication.model.MovieModel

interface IMovieDomain {

    open fun loadList(): List<MovieModel>

    open fun loadDetails(id: Int): MovieModel

}

class MovieDomainFactory {

    companion object {
        fun createMovieDomain() : IMovieDomain {
            return MovieDomain()
        }
    }

}

class MovieDomain : IMovieDomain {

    private var movieApi: IMovieApi?= null

    constructor() {
        movieApi = MovieApiFactory.createApi()
    }

    override fun loadList(): List<MovieModel> {

        var modelList: MutableList<MovieModel> = ArrayList<MovieModel>()
        return modelList
    }

    override fun loadDetails(id: Int): MovieModel {
        return MovieModel()
    }

}