package com.ksw.movietrend.ui.upcoming

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
 * Created by KSW on 2021-02-10
 */

class UpcomingViewModel @ViewModelInject constructor(
    movieRepository: MovieRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _upcomingMovies = MutableLiveData<NetworkResource<List<Movie>>>()

    val upcomingMovies: LiveData<NetworkResource<List<Movie>>>
        get() = _upcomingMovies

    init {
        compositeDisposable.add(
            movieRepository.getUpcomingMovie()
                .doOnSubscribe { _upcomingMovies.value = NetworkResource.Loading(null) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                        movies -> _upcomingMovies.value = NetworkResource.Success(movies.results) },
                    {
                        _upcomingMovies.value = NetworkResource.Error("Network Error!", null)
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}