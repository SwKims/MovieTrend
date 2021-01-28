package com.ksw.movietrend.network

import com.ksw.movietrend.model.Movies
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Created by KSW on 2021-01-28
 */

//https://api.themoviedb.org/3/trending/all/day?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR

interface MovieServiceApi {

    @GET("trending/all/day?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR")
    fun getTrendingMovie() : Single<Movies>
}