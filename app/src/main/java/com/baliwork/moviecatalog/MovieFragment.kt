package com.baliwork.moviecatalog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.baliwork.moviecatalog.adapter.MovieAdapter
import com.baliwork.moviecatalog.interfaces.MovieClickListener
import com.baliwork.moviecatalog.model.Movie
import com.baliwork.moviecatalog.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieFragment : Fragment(), MovieClickListener {

    private lateinit var movieViewModel: MovieViewModel
    companion object {
        const val TYPE_MOVIE = "movie"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel = ViewModelProviders.of(this)
            .get(MovieViewModel::class.java)
        return inflater
            .inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rv_movie.setHasFixedSize(true)
        movieViewModel.setMovie()
        movieViewModel.getMovies().observe(viewLifecycleOwner, showMovie)
    }

    private val showMovie = Observer<List<Movie>> {movies->
        val movieAdapter =
            view?.context?.let { MovieAdapter(it, movies, this) }
        view?.rv_movie?.adapter = movieAdapter
    }

    override fun onClick(position: Int, movies: List<Movie>) {
        val intent = Intent(view?.context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE_ID, movies[position].id)
        intent.putExtra(DetailActivity.TYPE, TYPE_MOVIE)
        startActivity(intent)
    }
}