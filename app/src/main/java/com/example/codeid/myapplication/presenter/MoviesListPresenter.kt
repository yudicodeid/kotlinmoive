package com.example.codeid.myapplication.presenter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.example.codeid.myapplication.adapter.MovieListAdapter
import com.example.codeid.myapplication.domain.IMovieDomain
import com.example.codeid.myapplication.domain.IMovieDomainListener
import com.example.codeid.myapplication.domain.MovieDomain
import com.example.codeid.myapplication.domain.MovieDomainFactory
import com.example.codeid.myapplication.model.MovieModel
import com.example.codeid.myapplication.view.IMoviesListView
import java.util.*
import kotlin.collections.ArrayList


class MoviesListPresenter(pContext: Context,
                          pView: IMoviesListView) :
        IMoviesListPresenter, IMovieDomainListener {


    private  val context: Context = pContext
    private val view: IMoviesListView = pView
    private var domain: IMovieDomain = MovieDomainFactory.createMovieDomain()
    private var listData: MutableList<MovieModel> = ArrayList()

    init {

        domain.setMovieDomainListener(this)

    }


    override fun onMovieSelected(movieModel: MovieModel) {
        view.startDetailsView(movieModel)
    }


    override fun onListDataUpdated(data: List<MovieModel>) {

        listData = data as MutableList<MovieModel>
        view.gridview.adapter = MovieListAdapter(context,
                view.itemLayout,
                listData,
                this)

        view.unloading()

    }

    override fun loadTopRated() {

        view.loading()

        domain.loadTopRated(1)
        
    }

    override fun loadPopular() {

        view.loading()

        domain.loadPopularList(1)

    }

    override fun getBaseImgPath(): String {
        return MovieDomain.getBaseImgPath()
    }


}