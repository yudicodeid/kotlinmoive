package com.example.codeid.myapplication.domain

import android.util.Log
import com.example.codeid.myapplication.api.MovieApiModel
import com.example.codeid.myapplication.db.DbHelper
import com.example.codeid.myapplication.db.IMovieTable
import com.example.codeid.myapplication.db.MovieTable


class MovieDbCache(val db: DbHelper) {

    fun insertFromListApiModel(list: List<MovieApiModel>) {

        for( model: MovieApiModel in list) {

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
                    Log.e(MovieDomain.TAG, e.message)
                }
            }
        }
    }

}