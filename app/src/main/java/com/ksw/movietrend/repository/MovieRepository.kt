package com.ksw.movietrend.repository

import com.ksw.movietrend.model.*
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
    private val movieServiceApi: MovieServiceApi
) {
    fun getTrendingMovie(): Single<Movies> {
        return movieServiceApi.getTrendingMovie()
            .subscribeOn(Schedulers.io())
    }

    fun getUpcomingMovie(): Single<Movies> {
        return movieServiceApi.getUpcomingMovie()
            .subscribeOn(Schedulers.io())
    }

    fun getNowPlayingMovie(): Single<Movies> {
        return movieServiceApi.getNowPlayingMovie()
            .subscribeOn(Schedulers.io())
    }

    fun getMovieDetails(movieId: Long): Single<Movie> {
        return movieServiceApi.getMovie(movieId)
            .subscribeOn(Schedulers.io())
    }

    fun getCastActorDetails(castId: Long): Single<Cast> {
        return movieServiceApi.getCastDetails(castId)
            .subscribeOn(Schedulers.io())
    }

    fun getPersonImages(castId: Long): Single<List<ProfileImage>> {
        return movieServiceApi.getPersonImages(castId)
            .subscribeOn(Schedulers.io())
            .map {
                it.profiles
            }
    }

    fun getPersonMovies(castId: Long): Single<List<ProfileMovieImage>> {
        return movieServiceApi.getPersonMovies(castId)
            .subscribeOn(Schedulers.io())
            .map {
                it.profilesMovie
            }
    }


    // trailer
    fun getMovieTrailer(movieId: Long): Single<Trailer> {
        return movieServiceApi.getMovieTrailer(movieId)
            .subscribeOn(Schedulers.io())

    }

}