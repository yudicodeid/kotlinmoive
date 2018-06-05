package com.example.codeid.myapplication.domain

import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.codeid.myapplication.api.MovieApiModel
import com.example.codeid.myapplication.db.DbHelper
import com.example.codeid.myapplication.db.MovieTable
import com.example.codeid.myapplication.db.PopularMovieTable
import com.example.codeid.myapplication.db.TopRatedMovieTable


class TopRatedMovieDbCache(val db:DbHelper)  {


    fun insertFromListApiModel(list: List<MovieApiModel>) {

        var topRated = TopRatedMovieTable(db.writableDatabase)
        topRated.truncate()

        for( model:MovieApiModel in list) {

            try {

                var topRated = TopRatedMovieTable(db.readableDatabase)
                topRated.movieID = model.id

                if( !topRated.findByMovieID() ) {

                    topRated = TopRatedMovieTable(db.writableDatabase)
                    topRated.movieID = model.id
                    topRated.add()

                }


            }catch(e:Exception) {
                Log.e(MovieDomain.TAG, e.message)
            }
        }
    }


    fun loadList(): List<MovieTable> {

        var readDb: SQLiteDatabase? = null
        var listMovieTable:List<MovieTable> = arrayListOf()

        try {

            readDb = db.readableDatabase

            val topRated = TopRatedMovieTable(readDb)
            listMovieTable = topRated.list()


        }catch (e:Exception){
            Log.e(MovieDomain.TAG, e.message)
        }finally {
            readDb?.close()
        }

        return listMovieTable


    }

}