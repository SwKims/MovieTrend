package com.ksw.movietrend.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val id : Long,
    val video : Boolean,
    @Json(name = "vote_count")
    val voteCount : Long,
    @Json(name = "vote_average")
    val voteAverage: Double,
    val title : String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    val adult: Boolean,
    val overview : String,
    @Json(name = "poster_path")
    val posterPath: String,
    val popularity: Double,
    @Json(name = "media_type")
    val mediaType: String,
)

/*
@JsonClass(generateAdapter = true)
data class Result(
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "first_air_date")
    val firstAirDate: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @Json(name = "media_type")
    val mediaType: String,
    val name: String,
    @Json(name = "origin_country")
    val originCountry: List<String>,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)*/
