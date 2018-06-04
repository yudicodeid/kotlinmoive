package com.example.codeid.myapplication.presenter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.example.codeid.myapplication.adapter.MovieListAdapter
import com.example.codeid.myapplication.domain.IMovieDomain
import com.example.codeid.myapplication.domain.IMovieDomainListener
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
    private var domain: IMovieDomain? =  null
    private var listData: MutableList<MovieModel> = ArrayList()

    init {
        view.gridview.adapter = MovieListAdapter(context, view.itemLayout, listData)
        domain = MovieDomainFactory.createMovieDomain()
        domain?.setMovieDomainListener(this)

    }

    override fun onListDataUpdated(data: List<MovieModel>) {

        listData = data as MutableList<MovieModel>

        view.gridview.adapter = MovieListAdapter(context, view.itemLayout, listData)

    }

    override fun loadData() {
        domain?.loadPopularList(1)
    }

    override fun onItemSelectedListener() {
    }


}