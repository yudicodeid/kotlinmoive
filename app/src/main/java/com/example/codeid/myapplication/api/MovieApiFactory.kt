package com.example.codeid.myapplication.api

class MovieApiFactory {

    companion object {

        fun createApi(): IMovieApi {

            return MovieApi()
        }

    }
}

