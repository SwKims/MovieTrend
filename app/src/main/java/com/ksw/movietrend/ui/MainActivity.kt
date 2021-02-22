package com.ksw.movietrend.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ksw.movietrend.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


private const val ID = "movieId"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var movieId = 0L
    private var key = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieId = intent.getLongExtra(ID, 0L)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.landingFragment -> showBottomNav()
                R.id.upcomingFragment -> showBottomNav()
                R.id.trendingFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }

    }

    private fun showBottomNav() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        bottomNavigationView.visibility = View.GONE
    }

}