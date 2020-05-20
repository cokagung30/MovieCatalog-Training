package com.baliwork.moviecatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.baliwork.moviecatalog.adapter.MovieAdapter
import com.baliwork.moviecatalog.model.Movie
import com.baliwork.moviecatalog.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel

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
            view?.context?.let { MovieAdapter(it, movies) }
        view?.rv_movie?.adapter = movieAdapter
    }
}