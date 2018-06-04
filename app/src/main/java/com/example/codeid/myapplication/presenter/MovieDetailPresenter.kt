package com.example.codeid.myapplication.presenter

import com.example.codeid.myapplication.domain.MovieDomain
import com.example.codeid.myapplication.model.MovieModel
import com.example.codeid.myapplication.view.IMovieDetailView

class MovieDetailPresenter(val view:IMovieDetailView) : IMovieDetailPresenter {

    override fun loadMovieData(movieModel: MovieModel) {

        view.ratingValue = movieModel.voteAverage

        view.movieOverview = movieModel.overview

        view.movieTitle = movieModel.title

        view.poster = movieModel.posterPath
    }

    override fun getBaseImgPath(): String {

        return MovieDomain.getBaseImgPath()

    }

}