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


class MoviesListPresenter(val context: Context,
                          val view: IMoviesListView) :
        IMoviesListPresenter, IMovieDomainListener {


    private var domain: IMovieDomain = MovieDomainFactory.createMovieDomain(context)
    private var listData: MutableList<MovieModel> = ArrayList<MovieModel>()
    private var _isInternetConnected : Boolean = false
    private var firstLoaded : Int = 0
    private var isLoading : Boolean = false


    override var isInternetConnected: Boolean
        get() = _isInternetConnected
        set(value) {
            _isInternetConnected = value
        }

    private var currListMode: ListMode = ListMode.POPULAR_LIST


    init {
        domain.setMovieDomainListener(this)
    }



    override fun setupListView() {
        view.setupListView()
    }
    override fun setupConnectivityListener() {
        view.setConnectivityListener()
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

        isLoading = false

    }


    private var isCached : Boolean
        get() = !isInternetConnected
        set(value) {}


    override fun loadListData() {

        firstLoaded++
        isLoading = true
        view.loading()

        if (currListMode ==  ListMode.POPULAR_LIST) {
            domain.loadPopularList(1, isCached)

        } else if(currListMode == ListMode.TOP_RATED ) {
            domain.loadTopRated(1, isCached)
        }

    }

    override fun getBaseImgPath(): String {
        return MovieDomain.getBaseImgPath()
    }

    override fun updateConnectionStatus(isConnected: Boolean) {

        if (!isConnected && !isInternetConnected) {
            isInternetConnected = false
            view.showAlertNetworkError()
        } else {

            if(firstLoaded>1 && !isLoading) {
                loadListData()
            }
        }

    }

    override fun changeListMode(mode: ListMode) {
        currListMode = mode
        loadListData()
    }
}