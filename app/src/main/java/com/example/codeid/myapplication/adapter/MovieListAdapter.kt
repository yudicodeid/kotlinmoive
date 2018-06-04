package com.example.codeid.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codeid.myapplication.model.MovieModel
import com.example.codeid.myapplication.presenter.IMoviesListPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_adapter.view.*



class MovieListAdapter(val context: Context,
        val layout: Int,
        val items: List<MovieModel>,
        val presenter: IMoviesListPresenter) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder {

        return MovieViewHolder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position], position, presenter)
    }

}


class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    fun bind(model: MovieModel, position: Int, presenter: IMoviesListPresenter) {

        itemView.movie_title.text = model.title

        Picasso.get().load(presenter.getBaseImgPath() + model.posterPath).into(itemView.movie_poster)

        itemView.setOnClickListener{ presenter.onMovieSelected(model) }
    }

}