package com.example.codeid.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codeid.myapplication.R
import com.example.codeid.myapplication.model.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_adapter.view.*


class MovieListAdapter(val context: Context,
        val layout: Int,
        private val items: List<MovieModel>) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder {

        return MovieViewHolder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val baseImgPath = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/"

    fun bind(model: MovieModel, position: Int) {

        itemView.movie_title.text = model.Title

        Picasso.get().load(baseImgPath + model.PosterPath).into(itemView.movie_poster)

        //itemView.setOnClickListener{ listener.onItemClick(android) }
    }

}