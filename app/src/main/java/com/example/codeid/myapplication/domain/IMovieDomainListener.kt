package com.example.codeid.myapplication.domain

import com.example.codeid.myapplication.model.MovieModel

interface IMovieDomainListener {

    fun onListDataUpdated(data : List<MovieModel>)

}