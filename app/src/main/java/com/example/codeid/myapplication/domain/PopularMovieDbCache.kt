package com.example.codeid.myapplication.domain

import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.codeid.myapplication.api.MovieApiModel
import com.example.codeid.myapplication.db.DbHelper
import com.example.codeid.myapplication.db.MovieTable
import com.example.codeid.myapplication.db.PopularMovieTable


class PopularMovieDbCache(val db:DbHelper)  {


    fun insertFromListApiModel(list: List<MovieApiModel>) {

        var popular = PopularMovieTable(db.writableDatabase)
        popular.truncate()

        for( model:MovieApiModel in list) {

            try {

                var popularMovie = PopularMovieTable(db.readableDatabase)
                popularMovie.movieID = model.id

                if(!popularMovie.findByMovieID()) {

                    popularMovie = PopularMovieTable(db.writableDatabase)
                    popularMovie.movieID = model.id
                    popularMovie.add()

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

            val popularMovieTable = PopularMovieTable(readDb)

            listMovieTable = popularMovieTable.list()


        }catch (e:Exception){
            Log.e(MovieDomain.TAG, e.message)
        }finally {
            readDb?.close()
        }

        return listMovieTable


    }
}