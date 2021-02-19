package com.ksw.movietrend.ui.popular

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksw.movietrend.model.Movie
import com.ksw.movietrend.model.NetworkResource
import com.ksw.movietrend.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class LandingViewModel @ViewModelInject constructor(
    movieRepository: MovieRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _trendingMovies = MutableLiveData<NetworkResource<List<Movie>>>()

    val trendingMovies: LiveData<NetworkResource<List<Movie>>>
        get() = _trendingMovies

    init {
        compositeDisposable.add(
            movieRepository.getTrendingMovie()
                .doOnSubscribe { _trendingMovies.value = NetworkResource.Loading(null) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                        movies -> _trendingMovies.value = NetworkResource.Success(movies.results) },
                    { t ->
                        _trendingMovies.value = NetworkResource.Error("Network Error!", null)
                    })
        )

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}