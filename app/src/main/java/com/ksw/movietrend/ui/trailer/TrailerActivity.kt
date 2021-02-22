//package com.ksw.movietrend.ui.trailer
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.os.PersistableBundle
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.android.synthetic.main.movie_trailer.*
//
///**
// * Created by KSW on 2021-02-19
// */
//
//private const val ID = "id"
//
//@AndroidEntryPoint
//class TrailerActivity : AppCompatActivity() {
//
//    private val movieTrailerViewModel: MovieTrailerViewModel by viewModels()
//
//    private var key = ""
//    private var movieId = 0L
//
//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//
//        movieId = intent.getLongExtra(ID, 0L)
//
//        watch_trailer.setOnClickListener {
//            watchTrailerWithYoutube()
//        }
//
//    }
//
//    private fun watchTrailerWithYoutube() {
//        if (key != "" && key!=null) {
//            startActivity(
//                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$key"))
//            )
//        } else {
//            Toast.makeText(this, "No trailer available!", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//}