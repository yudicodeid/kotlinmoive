package com.example.codeid.myapplication.view

import android.support.v7.widget.RecyclerView
import com.example.codeid.myapplication.model.MovieModel

interface IMoviesListView {

    var gridview: RecyclerView

    var itemLayout: Int

    fun startDetailsView(movieModel: MovieModel)

    fun loading()

    fun unloading()

}