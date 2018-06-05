package com.example.codeid.myapplication.domain

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.codeid.myapplication.api.*
import com.example.codeid.myapplication.db.DbHelper
import com.example.codeid.myapplication.db.MovieTable
import com.example.codeid.myapplication.db.PopularMovieTable
import com.example.codeid.myapplication.db.TopRatedMovieTable
import com.example.codeid.myapplication.model.MovieModel
import java.util.*



class MovieDomainFactory {

    companion object {

        fun createMovieDomain(context:Context) : IMovieDomain {
            return MovieDomain(context)
        }

    }

}



class MovieDomain(private val context:Context): IMovieDomain, IMovieApiListener {


    private var movieApi: IMovieApi

    private val popularMovieMemData: MemoryData = MemoryData()
    private val topRatedMovieMemData: MemoryData = MemoryData()
    private var movieDbCache : MovieDbCache
    private var popularMovieDbCache : PopularMovieDbCache
    private var topRatedMovieDbCache : TopRatedMovieDbCache


    private lateinit var listener: IMovieDomainListener

    private var db:DbHelper

    companion object {

        val TAG:String  = "MovieDomain"

        fun getBaseImgPath(): String {
            return "https://image.tmdb.org/t/p/w185_and_h278_bestv2/"
        }
    }

    init {

        movieApi = MovieApiFactory.createApi()
        movieApi.setMovieApiListener(this)

        db = DbHelper(context)

        movieDbCache = MovieDbCache(db)
        popularMovieDbCache = PopularMovieDbCache(db)
        topRatedMovieDbCache = TopRatedMovieDbCache(db)

    }

    override fun setMovieDomainListener(listener: IMovieDomainListener) {
        this.listener = listener
    }


    override fun onListDataPop(listApiModel: List<MovieApiModel>, requestType:RequestType) {

        var writeDb: SQLiteDatabase? = null

        try {

            writeDb = db.writableDatabase
            movieDbCache.insertFromListApiModel(listApiModel)


            if (requestType == RequestType.POPULAR_LIST) {

                popularMovieDbCache.insertFromListApiModel(listApiModel)
                popularMovieMemData.updateData(listApiModel)
                this.listener.onListDataUpdated(popularMovieMemData.data)

            }
            else if(requestType == RequestType.TOP_RATED) {

                topRatedMovieDbCache.insertFromListApiModel(listApiModel)
                topRatedMovieMemData.updateData(listApiModel)
                this.listener.onListDataUpdated(topRatedMovieMemData.data)

            }

        }catch(e:Exception) {
            Log.e(TAG, e.message)
        }finally {
            writeDb?.close()
        }
    }


    private fun loadPopularCache() {


        try {

            var listMovieTable = popularMovieDbCache.loadList()

            popularMovieMemData.clearCache()

            popularMovieMemData.updateFromLocal(listMovieTable)

            this.listener.onListDataUpdated(popularMovieMemData.data)

        }catch (e:Exception){
            Log.e(TAG, e.message)
        }

    }

    private fun loadTopRatedCache() {

        try {

            var listMovieTable = topRatedMovieDbCache.loadList()

            topRatedMovieMemData.clearCache()

            topRatedMovieMemData.updateFromLocal(listMovieTable)

            this.listener.onListDataUpdated(topRatedMovieMemData.data)


        }catch (e:Exception){
            Log.e(TAG, e.message)
        }

    }


    override fun loadPopularList(page: Int, cached:Boolean) {

        if (!cached) {
            this.movieApi.getPopularList(page)
        } else {
            loadPopularCache()
        }

    }

    override fun loadTopRated(page: Int, cached:Boolean) {

        if(!cached) {
            this.movieApi.getTopRated(page)
        } else {
            loadTopRatedCache()
        }

    }


}