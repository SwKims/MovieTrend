//package com.ksw.movietrend.ui.trailer
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import com.ksw.movietrend.R
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.android.synthetic.main.movie_trailer.*
//
///**
// * Created by KSW on 2021-02-19
// */
//
//private const val ID = "movieId"
//
//@AndroidEntryPoint
//class TrailerView :Fragment(R.layout.movie_trailer) {
//
//    private val viewModel: MovieTrailerViewModel by viewModels()
//
//    private var movieId = 0L
//    private var key = ""
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        watch_trailer.setOnClickListener {
//            watchTrailerWithYoutube()
//        }
//    }
//
//    private fun watchTrailerWithYoutube() {
//        if (key != "" && key!=null) {
//            startActivity(
//                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$key"))
//            )
//        } else {
//            Toast.makeText(context, "No trailer available!", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//}