package com.example.codeid.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codeid.myapplication.R
import com.example.codeid.myapplication.model.MovieModel
import kotlinx.android.synthetic.main.movie_adapter.view.*


class MovieListAdapter(
        val context: Context,
        private val items: List<MovieModel>) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(model: MovieModel, position: Int) {
        //itemView.movie_poster = model.Title
        itemView.movie_title.text = model.Title
        //itemView.setOnClickListener{ listener.onItemClick(android) }
    }

}