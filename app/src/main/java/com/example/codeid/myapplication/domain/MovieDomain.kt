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

    private var movieApi: IMovieApi?= null
    private var listData: MutableList<MovieModel> = ArrayList()
    private var listener: IMovieDomainListener? = null

    constructor() {

        movieApi = MovieApiFactory.createApi()
        movieApi?.setMovieApiListener(this)
    }

    override fun setMovieDomainListener(listener: IMovieDomainListener) {
        this.listener = listener
    }

    override fun onListDataPop(list: List<MovieApiModel>) {


        for( model:MovieApiModel in list) {

            var movieModel = MovieModel()
            movieModel.ID = model.id
            movieModel.Title = model.title
            movieModel.Overview = model.overview
            movieModel.PosterPath = model.poster_path
            movieModel.VoteAverage = model.vote_average

            listData.add(movieModel)

        }

        this.listener?.onListDataUpdated(listData)

    }

    override fun loadPopularList(page: Int) {
        this.movieApi?.getPopularList(1)
    }

    override fun loadTopRated(page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDetails(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}