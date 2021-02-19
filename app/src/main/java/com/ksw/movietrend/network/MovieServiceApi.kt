package com.ksw.movietrend.network

import com.ksw.movietrend.model.*
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

    @GET("movie/upcoming")
    fun getUpcomingMovie(): Single<Movies>

    @GET("movie/{movieId}?append_to_response=credits")
    fun getMovie(@Path("movieId") movieId: Long): Single<Movie>

    @GET("person/{id}")
    fun getCastDetails(@Path("id") castId: Long): Single<Cast>

    @GET("person/{id}/images")
    fun getPersonImages(@Path("id") castId: Long): Single<Profile>

    @GET("person/{personId}/movie_credits")
    fun getPersonMovies(@Path ("id") castId: Long): Single<ProfileMovie>

}

// https://api.themoviedb.org/3/search/movie?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR&query=Avengers&page=1&include_adult=false


// 하정우 출연작
// https://api.themoviedb.org/3/person/75913/movie_credits?api_key=4defd070082d28cda9bb902140bcf8d0&language=en-US
// https://api.themoviedb.org/3/person/75913/movie_credits?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR