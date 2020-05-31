package com.baliwork.moviecatalog.favoriteui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.baliwork.moviecatalog.DetailActivity
import com.baliwork.moviecatalog.MovieFragment
import com.baliwork.moviecatalog.R
import com.baliwork.moviecatalog.adapter.MovieAdapter
import com.baliwork.moviecatalog.interfaces.MovieClickListener
import com.baliwork.moviecatalog.model.Movie
import com.baliwork.moviecatalog.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieFavoriteFragment : Fragment(), MovieClickListener {

    private lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rv_movie.setHasFixedSize(true)
        movieViewModel.getAllFavoriteMovie().observe(viewLifecycleOwner, showFavoriteMovie)
    }

    private val showFavoriteMovie = Observer<List<Movie>> {
        if (it.isNotEmpty()) {
            val adapter = view?.context?.let { context -> MovieAdapter(context, it, this) }
            view?.rv_movie?.adapter = adapter
        } else {
            Toast.makeText(view?.context, "Data Favorite Movie Kosong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(position: Int, movies: List<Movie>) {
        val intent = Intent(view?.context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE_ID, movies[position].id)
        intent.putExtra(DetailActivity.TYPE, MovieFragment.TYPE_MOVIE)
        startActivity(intent)
    }
}