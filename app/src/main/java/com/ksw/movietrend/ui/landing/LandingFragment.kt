package com.ksw.movietrend.ui.landing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ksw.movietrend.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.landing_fragment.*

@AndroidEntryPoint
class LandingFragment : Fragment(R.layout.landing_fragment) {

    lateinit var movieAdapter: MovieAdapter

    private val landingViewModel by viewModels<LandingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        recyclerview_movie.layoutManager = LinearLayoutManager(requireContext())
        recyclerview_movie.adapter = movieAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        landingViewModel.trendingMovies.observe(viewLifecycleOwner, Observer {
            movieAdapter.setMovies(it)
        })
    }

}