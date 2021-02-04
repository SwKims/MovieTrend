package com.ksw.movietrend.repository

import com.ksw.movietrend.model.Movie
import com.ksw.movietrend.model.Movies
import com.ksw.movietrend.network.MovieServiceApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by KSW on 2021-01-29
 */

@Singleton
class MovieRepository @Inject constructor(
    private val movieServiceApi : MovieServiceApi
) {
    fun getTrendingMovie() : Single<Movies> {
        return movieServiceApi.getTrendingMovie()
            .subscribeOn(Schedulers.io())
    }

    fun getMovieDetails(movieId: Long) : Single<Movie> {
        return movieServiceApi.getMovie(movieId)
            .subscribeOn(Schedulers.io())
    }

}