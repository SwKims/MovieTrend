package com.ksw.movietrend.network

import com.ksw.movietrend.model.Movie
import com.ksw.movietrend.model.Movies
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by KSW on 2021-01-28
 */

// https://api.themoviedb.org/3/trending/all/day?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR
// https://api.themoviedb.org/3/trending/movie/day?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR
// https://api.themoviedb.org/3/movie/popular?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR


interface MovieServiceApi {
    /*// trending은 일부 작품이 한글설명이 없어 popular로 대체
    @GET("trending/all/day?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR")*/

    //    @GET("movie/popular?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR")

    //    @GET("trending/all/day?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR")

    @GET("movie/popular")
    fun getTrendingMovie(): Single<Movies>

    @GET("movie/{movieId}")
    fun getMovie(@Path("movieId") movieId: Long): Single<Movie>


}