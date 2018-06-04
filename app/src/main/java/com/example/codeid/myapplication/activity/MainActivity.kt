package com.example.codeid.myapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.GridView
import android.widget.TextView
import com.example.codeid.myapplication.R
import com.example.codeid.myapplication.view.IMoviesListView
import com.example.codeid.myapplication.presenter.IMoviesListPresenter
import com.example.codeid.myapplication.presenter.MoviesListPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMoviesListView {

    var presenter:IMoviesListPresenter? = null

    //private lateinit var linearLayoutManager:LinearLayoutManager

    private lateinit var gridLayoutManager: GridLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //linearLayoutManager = LinearLayoutManager(this)

        gridLayoutManager = GridLayoutManager(this,2)

        //rv_moviesList.layoutManager = linearLayoutManager

        rv_moviesList.layoutManager = gridLayoutManager

        presenter = MoviesListPresenter(this.applicationContext, this)

        presenter?.loadData()

    }

    override var gridview: RecyclerView

        get() = findViewById(R.id.rv_moviesList)

        set(value) {}


    override var itemLayout: Int

        get() = R.layout.movie_adapter
        set(value) {}

}
