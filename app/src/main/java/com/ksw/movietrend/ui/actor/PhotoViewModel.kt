package com.ksw.movietrend.ui.actor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ksw.movietrend.model.Cast
import com.ksw.movietrend.model.NetworkResource
import com.ksw.movietrend.model.ProfileImage
import com.ksw.movietrend.repository.MovieRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by KSW on 2021-02-08
 */

class PhotoViewModel @AssistedInject constructor(
    movieRepository: MovieRepository,
    @Assisted private val castId: Long
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _photo = MutableLiveData<NetworkResource<List<ProfileImage>>>()
    val photo: LiveData<NetworkResource<List<ProfileImage>>>
        get() = _photo

    init {
        compositeDisposable.addAll(
            movieRepository.getPersonImages(castId)
                .doOnSubscribe { _photo.value = NetworkResource.Loading() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response -> _photo.value = NetworkResource.Success(response) },
                    { error -> _photo.value = NetworkResource.Error(error.message!!) }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(castId: Long): PhotoViewModel
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