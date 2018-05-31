package com.example.codeid.myapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.GridView
import android.widget.TextView
import com.example.codeid.myapplication.R
import com.example.codeid.myapplication.view.IMoviesListView
import com.example.codeid.myapplication.presenter.IMoviesListPresenter
import com.example.codeid.myapplication.presenter.MoviesListPresenter

class MainActivity : AppCompatActivity(), IMoviesListView {

    var presenter:IMoviesListPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MoviesListPresenter(this)
        presenter?.loadData()

    }

    override var gridview: RecyclerView

        get() = findViewById(R.id.rv_moviesList)

        set(value) {}


}
