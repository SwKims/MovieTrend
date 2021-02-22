//package com.ksw.movietrend.ui.trailer
//
//import androidx.hilt.Assisted
//import androidx.hilt.lifecycle.ViewModelInject
//import androidx.lifecycle.*
//import com.ksw.movietrend.model.NetworkResource
//import com.ksw.movietrend.model.Video
//import com.ksw.movietrend.repository.MovieRepository
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
//import io.reactivex.rxjava3.disposables.CompositeDisposable
//
///**
// * Created by KSW on 2021-02-19
// */
//
//private const val ID = "movieId"
//class MovieTrailerViewModel @ViewModelInject constructor(
//    private val movieRepository: MovieRepository,
//    @Assisted private val savedStateHandle: SavedStateHandle
//) : ViewModel() {
//
//    private val movieId = savedStateHandle.get<Long>(ID)!!
//
//    private val _trailer = MutableLiveData<NetworkResource<Video>>()
//    private val compositeDisposable = CompositeDisposable()
//
//    val trailer: LiveData<NetworkResource<Video>>
//        get() = _trailer
//
//    init {
//        if (savedStateHandle.contains("movieId")) {
//            val movieId = savedStateHandle.get<Long>("movieId")
//            compositeDisposable.addAll(
//                movieRepository.getMovieTrailer(movieId!!)
//                    .doOnSubscribe { _trailer.value = NetworkResource.Loading() }
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                        { trailer -> _trailer.value = NetworkResource.Success(trailer)},
//                        { error -> _trailer.value = NetworkResource.Error(error.message!!)}
//                    )
//            )
//        } else {
//            _trailer.value = NetworkResource.Error("OFF")
//        }
//    }
//
//
//    override fun onCleared() {
//        compositeDisposable.clear()
//        super.onCleared()
//    }
//
//
//}