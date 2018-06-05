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

    private var popularMovieMemData: MemoryData = MemoryData()

    private var topRatedMovieMemData: MemoryData = MemoryData()


    private lateinit var listener: IMovieDomainListener

    private var db:DbHelper

    companion object {

        fun getBaseImgPath(): String {
            return "https://image.tmdb.org/t/p/w185_and_h278_bestv2/"
        }
    }

    init {

        movieApi = MovieApiFactory.createApi()
        movieApi.setMovieApiListener(this)
        db = DbHelper(context)

    }

    override fun setMovieDomainListener(listener: IMovieDomainListener) {
        this.listener = listener
    }


    private fun insertPopularMovieTable(list: List<MovieApiModel>) {

        var popular = PopularMovieTable(db.writableDatabase)
        popular.truncate()

        for( model:MovieApiModel in list) {

            try {

                val popularMovie = PopularMovieTable(db.writableDatabase)
                popularMovie.movieID = model.id
                popularMovie.add()

            }catch(e:Exception) {
                Log.e("MovieDomain", e.message)
            }
        }
    }

    private fun insertTopRatedMovieTable(list: List<MovieApiModel>) {

        var topRated = TopRatedMovieTable(db.writableDatabase)
        topRated.truncate()

        for( model:MovieApiModel in list) {

            try {

                val topRated = TopRatedMovieTable(db.writableDatabase)
                topRated.movieID = model.id
                topRated.add()

            }catch(e:Exception) {
                Log.e("MovieDomain", e.message)
            }
        }
    }

    private fun insertIntoMovieTable(list: List<MovieApiModel>) {

        for( model:MovieApiModel in list) {

            var movie = MovieTable(db.readableDatabase)
            movie.id = model.id

            if (!movie.find()) {

                movie = MovieTable(db.writableDatabase)
                movie.id = model.id
                movie.title = model.title
                movie.posterPath = model.poster_path
                movie.ratingValue = model.vote_average
                movie.overview= model.overview

                try {
                    movie.add()
                }catch(e:Exception) {
                    Log.e("MovieDomain", e.message)
                }
            }

        }
    }


    override fun onListDataPop(listApiModel: List<MovieApiModel>, requestType:RequestType) {

        var writeDb: SQLiteDatabase? = null

        try {

            writeDb = db.writableDatabase

            var originalList = listApiModel.toMutableList()

            insertIntoMovieTable(listApiModel)

            if (requestType == RequestType.POPULAR_LIST) {

                insertPopularMovieTable(originalList)

                popularMovieMemData.updateData(listApiModel)

                this.listener.onListDataUpdated(popularMovieMemData.data)


            }
            else if(requestType == RequestType.TOP_RATED) {

                insertTopRatedMovieTable(originalList)

                topRatedMovieMemData.updateData(listApiModel)

                this.listener.onListDataUpdated(topRatedMovieMemData.data)

            }

        }catch(e:Exception) {
        }finally {
            writeDb?.close()
        }



    }

    override fun loadPopularList(page: Int) {

        this.movieApi.getPopularList(page)

    }

    override fun loadTopRated(page: Int) {

        this.movieApi.getTopRated(page)

    }


}