package com.ksw.movietrend.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by KSW on 2021-01-28
 */

@JsonClass(generateAdapter = true)
data class Movies(
    val page: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    val results: List<Movie>
)

@JsonClass(generateAdapter = true)
data class Movie(
    val id : Long?,
    val video : Boolean?,
    @Json(name = "vote_count")
    val voteCount : Long?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    val title : String?,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val adult: Boolean?,
    val overview : String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    val popularity: Double?,
    @Json(name = "media_type")
    val mediaType: String?,
)