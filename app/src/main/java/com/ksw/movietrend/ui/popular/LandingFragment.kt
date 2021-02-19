package com.ksw.movietrend.ui.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ksw.movietrend.R
import com.ksw.movietrend.adapter.MovieAdapter
import com.ksw.movietrend.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.landing_fragment.*
import kotlinx.android.synthetic.main.layout_loading.*

@AndroidEntryPoint
class LandingFragment : Fragment(R.layout.landing_fragment) {

    lateinit var movieAdapter: MovieAdapter

    private val landingViewModel by viewModels<LandingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        recyclerview_PopularMovie.layoutManager = LinearLayoutManager(requireContext())
        recyclerview_PopularMovie.adapter = movieAdapter

        /*recyclerview_movie.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
            isNestedScrollingEnabled = false
        }

        recyclerview_movie2.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
            isNestedScrollingEnabled = false
        }*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        landingViewModel.trendingMovies.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    showLoad(false)
                    movieAdapter.setMovies(it.data!!)
                }
                Status.LOADING -> {
                    showLoad(false)
//                    Snackbar.make(requireView(), "a", Snackbar.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    showLoad(true)
                    Snackbar.make(requireView(), "인터넷 연결을 해주세요.", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

    }

    // progressbar show
    private fun showLoad(isShow: Boolean) {
        if (isShow) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

}