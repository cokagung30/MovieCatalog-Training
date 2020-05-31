package com.baliwork.moviecatalog.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baliwork.moviecatalog.BuildConfig
import com.baliwork.moviecatalog.R
import com.baliwork.moviecatalog.interfaces.TvShowClickListener
import com.baliwork.moviecatalog.model.TvShow
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class TvShowAdapter(
    private val context: Context,
    private val tvShows: List<TvShow>,
    private val listener: TvShowClickListener
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = tvShows.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TvShowAdapter.ViewHolder, position: Int) {
        val poster = "${BuildConfig.URL_IMG}w185${tvShows[position].poster_path}"

        holder.itemView.tv_title_movie.text = tvShows[position].original_name
        holder.itemView.tv_rating_movie.text = "${tvShows[position].vote_average}"
        holder.itemView.rate_movie.rating = tvShows[position].vote_average.toFloat() / 2
        holder.itemView.tv_release_date.text = "Release : ${tvShows[position].first_air_date}"
        Glide.with(context).load(poster).into(holder.itemView.iv_poster)

        holder.itemView.item_movies.setOnClickListener {
            listener.onClick(position, tvShows)
        }
    }

}