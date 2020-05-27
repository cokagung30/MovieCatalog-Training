package com.baliwork.moviecatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.baliwork.moviecatalog.adapter.TvShowAdapter
import com.baliwork.moviecatalog.model.TvShow
import com.baliwork.moviecatalog.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show.view.*


class TvShowFragment : Fragment() {

    private lateinit var tvShowViewModel : TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rv_tv_show.setHasFixedSize(true)
        tvShowViewModel.setTvShow()
        tvShowViewModel.getTvShow().observe(viewLifecycleOwner, showTvShow)
    }

    private val showTvShow = Observer<List<TvShow>> {tvShow ->
        val tvShowAdapter = view?.context?.let { TvShowAdapter(it, tvShow) }
        view?.rv_tv_show?.adapter = tvShowAdapter
    }
}
