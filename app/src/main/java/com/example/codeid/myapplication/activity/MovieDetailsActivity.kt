package com.example.codeid.myapplication.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.codeid.myapplication.R
import com.example.codeid.myapplication.model.MovieModel
import com.example.codeid.myapplication.presenter.IMovieDetailPresenter
import com.example.codeid.myapplication.presenter.MovieDetailPresenter
import com.example.codeid.myapplication.view.IMovieDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(), IMovieDetailView {

    companion object {
        const val MOVIE = "MOVIE"
    }

    lateinit var presenter: IMovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        //val actionBar = actionBar
        //actionBar!!.setDisplayHomeAsUpEnabled(true)

        var movieModel = intent.getSerializableExtra(MOVIE) as MovieModel

        presenter = MovieDetailPresenter(this)
        presenter.loadMovieData(movieModel)

    }

    private var _ratingValue:Float = 0.0f

    override var ratingValue: Float
        get() = _ratingValue
        set(value) {
            _ratingValue = value
            rating_value.text = value.toString()
            //rating_bar.rating = value
        }


    override var movieTitle: String
        get() = movie_title.text.toString()
        set(value) {
            movie_title.text = value
        }


    override var movieOverview: String
        get() = movie_overview.text.toString()
        set(value) {
            movie_overview.text = value
        }

    private lateinit var _poster:String

    override var poster: String

        get() = _poster

        set(value) {
            _poster = value
            Picasso.get().load(presenter.getBaseImgPath() + _poster).into(movie_poster)
        }


}
