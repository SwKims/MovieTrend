package com.ksw.movietrend.ui.actor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ksw.movietrend.model.Cast
import com.ksw.movietrend.model.NetworkResource
import com.ksw.movietrend.repository.MovieRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by KSW on 2021-02-08
 */

class AboutViewModel @AssistedInject constructor(
    movieRepository: MovieRepository,
    @Assisted private val castId: Long
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _cast = MutableLiveData<NetworkResource<Cast>>()
    val cast: LiveData<NetworkResource<Cast>>
        get() = _cast

    init {
        compositeDisposable.addAll(
            movieRepository.getCastActorDetails(castId)
                .doOnSubscribe { _cast.value = NetworkResource.Loading() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response -> _cast.value = NetworkResource.Success(response) },
                    { error -> _cast.value = NetworkResource.Error(error.message!!) }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(castId: Long): AboutViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            castId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(castId) as T
            }

        }
    }

}