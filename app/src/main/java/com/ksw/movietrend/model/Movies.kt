package com.ksw.movietrend.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movies(
    val page: Long,
    @Json(name = "total_pages")
    val totalPages: Long,
    @Json(name = "total_results")
    val totalResults: Long,
    val results: List<Movie>
)

@JsonClass(generateAdapter = true)
data class Movie(
    val id: Long?,
    val video: Boolean?,
    @Json(name = "vote_count")
    val voteCount: Long?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    val title: String?,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val adult: Boolean?,
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    val popularity: Double?,
    @Json(name = "media_type")
    val mediaType: String?,
    val homepage: String?,
    val tagline: String?,
    // movie genre, time add
    val genres: List<Genre>?,
    val runtime: Long?,
    // credit add
    val credits: Credits?
)

// movie detail
@JsonClass(generateAdapter = true)
data class Genre(
    val id: Long,
    val name: String
)

// trailer
@JsonClass(generateAdapter = true)
data class Trailer(
    val id: Long?,
    val results: List<Result>?
)

@JsonClass(generateAdapter = true)
data class Result(
    val key: String?,
    val name: String?
)
