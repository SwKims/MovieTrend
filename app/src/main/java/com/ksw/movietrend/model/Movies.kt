package com.ksw.movietrend.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by KSW on 2021-01-28
 */

@JsonClass(generateAdapter = true)
data class Movies(
    val page: Int,
    val results: List<Result>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)