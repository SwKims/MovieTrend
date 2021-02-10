package com.ksw.movietrend.ui.actor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.ksw.movietrend.R
import com.ksw.movietrend.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject
import kotlin.properties.Delegates

private const val CAST_ID = "castId"

@AndroidEntryPoint
class AboutFragment : Fragment(R.layout.fragment_about) {
    private var castId by Delegates.notNull<Long>()

    @Inject
    lateinit var aboutViewModelFactory: AboutViewModel.AssistedFactory

    private val aboutViewModel: AboutViewModel by viewModels {
        AboutViewModel.provideFactory(
            aboutViewModelFactory,
            castId
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            castId = it.getLong(CAST_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh_about.isEnabled = false

        aboutViewModel.cast.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { cast ->

                        tv_Birthday.text =
                            if (!cast.birthday.isNullOrEmpty()) cast.birthday else getString(R.string.dash)

                        tv_Age.text =
                            if (!cast.birthday.isNullOrEmpty()) cast.birthday else getString(R.string.dash)

                        tv_PlaceOfBirth.text =
                            if (!cast.placeOfBirth.isNullOrEmpty()) cast.placeOfBirth else getString(
                                R.string.dash
                            )

                        tv_Department.text =
                            if (!cast.department.isNullOrEmpty()) cast.department else getString(R.string.dash)

                        tv_Biography.text =
                            if (!cast.biography.isNullOrEmpty()) cast.biography else getString(R.string.dash)

                        tvKnownAs.text =
                            if (!cast.knownAs.isNullOrEmpty()) cast.knownAs.joinToString(", ") else getString(
                                R.string.dash
                            )

                        cast.deathday?.let { deathday ->
                            tv_DeathDay.text = deathday
                            IIDeathDay.visibility = View.VISIBLE
                        }

                    }
                    showLoading(false)
                }
                Status.ERROR -> {
                    showLoading(false)
                    Snackbar.make(
                        requireView(), it.message!!, Snackbar.LENGTH_SHORT
                    ).show()
                }
                Status.LOADING -> showLoading(true)
            }
        }

    }

    private fun showLoading(loading: Boolean) {
        swipeRefresh_about.isRefreshing = loading

        if (!loading) {
            nsv_about.visibility = View.VISIBLE
        }
    }


    companion object {
        fun newInstance(castId: Long) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putLong(CAST_ID, castId)
                }
            }
    }
}