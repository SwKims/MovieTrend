package com.ksw.movietrend.ui.trending

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksw.movietrend.model.Movie
import com.ksw.movietrend.model.NetworkResource
import com.ksw.movietrend.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by KSW on 2021-03-26
 */

class TrendingViewModel @ViewModelInject constructor(
    movieRepository: MovieRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _nowPlayingMovies = MutableLiveData<NetworkResource<List<Movie>>>()

    val nowPlayingMovies: LiveData<NetworkResource<List<Movie>>>
        get() = _nowPlayingMovies

    init {
        compositeDisposable.add(
            movieRepository.getNowPlayingMovie()
                .doOnSubscribe { _nowPlayingMovies.value = NetworkResource.Loading(null) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movies -> _nowPlayingMovies.value = NetworkResource.Success(movies.results) },
                    {
                        _nowPlayingMovies.value = NetworkResource.Error("Error!", null)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}