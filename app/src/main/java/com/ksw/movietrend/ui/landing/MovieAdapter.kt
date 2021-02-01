package com.ksw.movietrend.ui.landing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksw.movietrend.R
import com.ksw.movietrend.glide.GlideApp
import com.ksw.movietrend.model.Movie
import com.ksw.movietrend.model.Movies
import com.ksw.movietrend.model.Result
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by KSW on 2021-01-29
 */

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies : List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() : Int = movies.size

    fun setMovies(movies : List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                GlideApp.with(iv_poster)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(iv_poster)
                tv_title.text = movie.title
                tv_releaseDate.text = movie.releaseDate
                tv_overView.text = movie.overview
            }

        }

        companion object {
            fun from(parent: ViewGroup) : MovieViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_movie, parent, false)
                return MovieViewHolder(itemView)
            }
        }
    }
}