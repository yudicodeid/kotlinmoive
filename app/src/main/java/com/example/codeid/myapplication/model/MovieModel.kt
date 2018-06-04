package com.example.codeid.myapplication.model

import java.io.Serializable

class MovieModel : Serializable{

    var id:Int = 0
    var title: String = ""
    var overview: String = ""
    var voteAverage: Float = 0.0f
    var posterPath: String = ""

}