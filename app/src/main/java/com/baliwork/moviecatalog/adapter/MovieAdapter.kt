package com.baliwork.moviecatalog.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baliwork.moviecatalog.BuildConfig
import com.baliwork.moviecatalog.R
import com.baliwork.moviecatalog.interfaces.MovieClickListener
import com.baliwork.moviecatalog.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (
    private val context: Context,
    private val movies: List<Movie>,
    private val listener: MovieClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
    //        Tambahkan Layout yang akan ditempelkan pada Recyclerview
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val urlPoster =
            "${BuildConfig.URL_IMG}w185${movies[position].poster_path}"
        holder.itemView.tv_title_movie.text = movies[position].title
        holder.itemView.tv_rating_movie.text = "${movies[position].vote_average}"
        holder.itemView.rate_movie.rating = movies[position]
            .vote_average.toFloat() / 2
        Glide.with(context).load(urlPoster).into(holder.itemView.iv_poster)
        holder.itemView.tv_release_date.text =
            "Release: ${movies[position].release_date}"

        holder.itemView.item_movies.setOnClickListener {
            listener.onClick(position, movies)
        }
    }

}