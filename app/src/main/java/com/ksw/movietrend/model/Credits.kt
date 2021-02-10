package com.ksw.movietrend.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by KSW on 2021-02-08
 */

// https://api.themoviedb.org/3/movie/464052/credits?api_key=4defd070082d28cda9bb902140bcf8d0&language=ko-KR

@JsonClass(generateAdapter = true)
data class Credits(
    val cast: List<Cast>
)

@Parcelize
@JsonClass(generateAdapter = true)
data class Cast(
    val id: Long,
    @Json(name = "cast_id")
    val castId: Long?,
    @Json(name = "credit_id")
    val creditId: String?,
    val character: String?,
    val gender: Int?,
    val name: String,
    @Json(name = "profile_path")
    val profilePath: String?,
    val birthday: String?,
    val deathday: String?,
    @Json(name = "place_of_birth")
    val placeOfBirth: String?,
    @Json(name = "known_for_department")
    val department: String?,
    @Json(name = "also_known_as")
    val knownAs : List<String>?,
    @Json(name = "biography")
    val biography: String?
) : Parcelable

// https://developers.themoviedb.org/3/people/get-person-details

@JsonClass(generateAdapter = true)
data class Profile(
    val profiles: List<ProfileImage>
)

@JsonClass(generateAdapter = true)
data class ProfileImage(
    val width: Int?,
    val height: Int?,
    @Json(name = "file_path")
    val path: String?,
    @Json(name = "aspect_ratio")
    val aspectRatio: Double?
)

// https://developers.themoviedb.org/3/people/get-person-images

@JsonClass(generateAdapter = true)
data class ProfileMovie(
    val profilesMovie: List<ProfileMovieImage>
)

@JsonClass(generateAdapter = true)
data class ProfileMovieImage(
    val title : String?
)