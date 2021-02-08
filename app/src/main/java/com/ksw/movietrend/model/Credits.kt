package com.ksw.movietrend.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by KSW on 2021-02-08
 */

// https://api.themoviedb.org/3/movie/464052/credits?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR

@JsonClass(generateAdapter = true)
data class Credits(
    val cast: List<Cast>
)

@JsonClass(generateAdapter = true)
data class Cast(
    val id: Long,
    @Json(name = "cast_id")
    val castId: Long,
    @Json(name = "credit_id")
    val creditId: String?,
    val character: String?,
    val gender: Int?,
    val name: String,
    @Json(name="profile_path")
    val profilePath: String?

)