package com.example.codeid.myapplication.domain

import com.example.codeid.myapplication.api.IMovieApi
import com.example.codeid.myapplication.api.IMovieApiListener
import com.example.codeid.myapplication.api.MovieApiFactory
import com.example.codeid.myapplication.api.MovieApiModel
import com.example.codeid.myapplication.model.MovieModel
import java.util.*



class MovieDomainFactory {

    companion object {

        fun createMovieDomain() : IMovieDomain {

            return MovieDomain()

        }

    }



}

class MovieDomain : Observable, IMovieDomain, IMovieApiListener {

    private lateinit var movieApi: IMovieApi
    private var listData: MutableList<MovieModel> = ArrayList()
    private lateinit var listener: IMovieDomainListener

    companion object {

        fun getBaseImgPath(): String {
            return "https://image.tmdb.org/t/p/w185_and_h278_bestv2/"
        }
    }


    constructor() {

        movieApi = MovieApiFactory.createApi()
        movieApi.setMovieApiListener(this)

    }

    override fun setMovieDomainListener(listener: IMovieDomainListener) {

        this.listener = listener

    }

    override fun onListDataPop(list: List<MovieApiModel>) {

        listData.clear()
        for( model:MovieApiModel in list) {

            var movieModel = MovieModel()
            movieModel.id = model.id
            movieModel.title = model.title
            movieModel.overview = model.overview
            movieModel.posterPath = model.poster_path
            movieModel.voteAverage = model.vote_average

            listData.add(movieModel)

        }

        this.listener?.onListDataUpdated(listData)

    }

    override fun loadPopularList(page: Int) {
        this.movieApi.getPopularList(page)
    }

    override fun loadTopRated(page: Int) {
        this.movieApi.getTopRated(page)
    }


}