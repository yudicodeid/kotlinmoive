package com.example.codeid.myapplication.presenter

import com.example.codeid.myapplication.domain.IMovieDomain
import com.example.codeid.myapplication.domain.IMovieDomainListener
import com.example.codeid.myapplication.domain.MovieDomainFactory
import com.example.codeid.myapplication.model.MovieModel
import com.example.codeid.myapplication.view.IMoviesListView
import java.util.*


class MoviesListPresenter(pView: IMoviesListView) :
        IMoviesListPresenter, IMovieDomainListener {

    private val view: IMoviesListView = pView
    private var domain: IMovieDomain? =  null

    init {

        domain = MovieDomainFactory.createMovieDomain()
        domain?.setMovieDomainListener(this)

    }

    override fun onListDataUpdated(data: List<MovieModel>) {

    }

    override fun loadData() {
        domain?.loadPopularList(1)
    }

    override fun onItemSelectedListener() {
    }


}