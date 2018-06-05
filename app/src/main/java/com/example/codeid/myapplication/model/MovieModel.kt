package com.example.codeid.myapplication.model

import com.example.codeid.myapplication.api.MovieApiModel
import com.example.codeid.myapplication.db.MovieTable
import java.io.Serializable

class MovieModel : Serializable{

    var id:Int = 0
    var title: String = ""
    var overview: String = ""
    var voteAverage: Float = 0.0f
    var posterPath: String = ""

    fun mapFromApiModel(apiModel: MovieApiModel) {

        id = apiModel.id
        title = apiModel.title
        overview = apiModel.overview
        posterPath = apiModel.poster_path
        voteAverage = apiModel.vote_average

    }

    fun mapFromTable(table: MovieTable) {

        id = table.id
        title = table.title
        overview = table.overview
        posterPath = table.posterPath
        voteAverage =  table.ratingValue

    }

}