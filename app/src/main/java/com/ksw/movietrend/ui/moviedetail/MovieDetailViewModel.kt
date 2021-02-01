package com.ksw.movietrend.ui.moviedetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ksw.movietrend.model.Movie
import com.ksw.movietrend.model.NetworkResource
import com.ksw.movietrend.repository.MovieRepository

/**
    @Assisted -> savedStateHandle 을 주입해줌.
    ( + 추가 없어도 주입이 가능하다.)
    https://two22.tistory.com/4
 */

class MovieDetailViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movie = MutableLiveData<NetworkResource<Movie>>()

    val movie: LiveData<NetworkResource<Movie>>
        get() = _movie

    init {
        if (savedStateHandle.contains("movieId")) {
            savedStateHandle.get<Long>("movieId")
        } else {
            _movie.value = NetworkResource.Error("OMG")
        }
    }
}