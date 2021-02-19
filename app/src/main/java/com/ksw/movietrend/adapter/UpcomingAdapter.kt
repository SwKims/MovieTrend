package com.ksw.movietrend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ksw.movietrend.R
import com.ksw.movietrend.glide.GlideApp
import com.ksw.movietrend.model.Movie
import com.ksw.movietrend.ui.popular.LandingFragmentDirections
import com.ksw.movietrend.ui.upcoming.UpcomingFragmentDirections
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by KSW on 2021-02-10
 */

class UpcomingAdapter : RecyclerView.Adapter<UpcomingAdapter.MovieViewHolder>() {

    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {

            // go detailView
            itemView.setOnClickListener {
                val directions =
                    UpcomingFragmentDirections.actionUpcomingFragmentToMovieDetailFragment(movie.id!!)
                it.findNavController().navigate(directions)
            }

            itemView.apply {
                GlideApp.with(iv_poster)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(iv_poster)
                tv_rate.text = movie.voteAverage.toString()
                tv_title.text = movie.title
                tv_releaseDate.text = movie.releaseDate
                tv_overView.text = movie.overview
            }

        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_movie, parent, false)
                return MovieViewHolder(itemView)
            }
        }
    }
}