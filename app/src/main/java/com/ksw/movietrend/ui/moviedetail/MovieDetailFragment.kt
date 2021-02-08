package com.ksw.movietrend.ui.moviedetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ksw.movietrend.R
import com.ksw.movietrend.adapter.CastAdapter
import com.ksw.movietrend.glide.GlideApp
import com.ksw.movietrend.model.Status
import com.ksw.movietrend.util.Constants.Companion.MAX_ACTOR_COUNT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_loading.*
import kotlinx.android.synthetic.main.movie_detail_fragment.*

// https://mparchive.tistory.com/175 ConstraintLayout
// https://stackoverflow.com/questions/22192291/how-to-change-the-status-bar-color-in-android statusbar

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.movie_detail_fragment) {

    private val viewModel: MovieDetailViewModel by viewModels()

    private lateinit var castAdapter: CastAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_back.setOnClickListener {
            view.findNavController().popBackStack()
        }

        castAdapter = CastAdapter()

        rv_castActor.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = castAdapter
            isNestedScrollingEnabled = false
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    showLoading(false)

                    val movie = it.data

                    GlideApp.with(iv_movieTitle)
                        .load("https://image.tmdb.org/t/p/original${movie?.backdropPath}")
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_error)
                        .into(iv_movieTitle)

                    GlideApp.with(iv_moviePoster)
                        .load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_error)
                        .into(iv_moviePoster)

                    movieTitle.text = movie?.title

                    if (movie?.genres != null && movie.genres.isNotEmpty()) {
                        val genres = movie.genres.joinToString(
                            separator = " | ",
                            transform = { genre -> genre.name }
                        )
                        movieGenres.text = genres
                    } else {
                        movieGenres.visibility = View.GONE
                    }

                    if (movie?.runtime != null) {
                        movieRuntime.text = movie.runtime.toString()
                    } else {
                        movieRuntime.visibility = View.GONE
                    }

                    if (movie?.releaseDate != null && movie.releaseDate.isNotBlank()) {
                        movieReleaseDate.text = movie.releaseDate
                    } else {
                        movieReleaseDate.visibility = View.GONE
                    }

                    movie?.voteAverage?.let { rating ->
                        movieRating.rating = (rating / 2).toFloat()
                    }

                    movieVoteCount.text = movie?.voteCount.toString()
                    movieOverview.text = movie?.overview

                    val casts = movie?.credits?.cast

                    if (casts != null && casts.isNotEmpty()) {

                        val numberOfActor =
                            if (casts.size <= MAX_ACTOR_COUNT) {
                                casts.size
                            } else {
                                MAX_ACTOR_COUNT
                            }

                        castAdapter.submitList(casts.take(numberOfActor))
                    }

                }
                Status.LOADING -> {
                    showLoading(true)
                }
                Status.ERROR -> {
                    showLoading(false)
                    Snackbar.make(
                        requireView(),
                        "Detail Movie!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }

    private fun showLoading(showLoading: Boolean) {
        loading.visibility =
            if (showLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

}