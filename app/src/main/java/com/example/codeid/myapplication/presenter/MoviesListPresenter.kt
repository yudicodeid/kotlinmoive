package com.example.codeid.myapplication.presenter

import com.example.codeid.myapplication.domain.IMovieDomain
import com.example.codeid.myapplication.domain.MovieDomainFactory
import com.example.codeid.myapplication.view.IMoviesListView
import com.example.codeid.myapplication.model.MovieModel


class MoviesListPresenter(pView: IMoviesListView) : IMoviesListPresenter {

    private val view: IMoviesListView = pView

    private val domain: IMovieDomain = MovieDomainFactory.createMovieDomain()

    init {

    }

    override fun loadData() {
        val movies:List<MovieModel> = domain.loadList()
    }

    override fun onItemSelectedListener() {
    }

}