package com.example.codeid.myapplication.activity

import android.app.ProgressDialog
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.example.codeid.myapplication.R
import com.example.codeid.myapplication.model.MovieModel
import com.example.codeid.myapplication.presenter.IMoviesListPresenter
import com.example.codeid.myapplication.presenter.MoviesListPresenter
import com.example.codeid.myapplication.view.IMoviesListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMoviesListView {

    private lateinit var presenter:IMoviesListPresenter

    private lateinit var gridLayoutManager: GridLayoutManager

    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        gridLayoutManager = GridLayoutManager(this,2)

        rv_moviesList.layoutManager = gridLayoutManager

        presenter = MoviesListPresenter(this.applicationContext, this)

        presenter.loadPopular()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater

        inflater.inflate(R.menu.options_menu, menu)

        return super.onCreateOptionsMenu(menu)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {

            R.id.menu_popular -> {
                presenter.loadPopular()
                return true
            }

            R.id.menu_top_rated -> {
                presenter.loadTopRated()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override var gridview: RecyclerView

        get() = findViewById(R.id.rv_moviesList)

        set(value) {}


    override var itemLayout: Int

        get() = R.layout.movie_adapter
        set(value) {}


    override fun startDetailsView(movieModel: MovieModel) {

        var intent = Intent(applicationContext, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.MOVIE, movieModel)
        startActivity(intent)

    }

    override fun loading() {
        progressDialog = ProgressDialog.show(this, "", "Please wait", true)

    }

    override fun unloading() {
        progressDialog.hide()
    }
}
