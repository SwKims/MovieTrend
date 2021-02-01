package com.ksw.movietrend.model

/**
 * Created by KSW on 2021-02-01
 */

// https://developer.android.com/jetpack/guide?hl=ko#addendum

sealed class NetworkResource<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NetworkResource<T>(Status.SUCCESS,data)
    class Loading<T>(data: T? = null) : NetworkResource<T>(Status.LOADING,data)
    class Error<T>(message: String, data: T? = null) : NetworkResource<T>(Status.ERROR ,data, message)
}

enum class Status {
    SUCCESS, LOADING, ERROR
}