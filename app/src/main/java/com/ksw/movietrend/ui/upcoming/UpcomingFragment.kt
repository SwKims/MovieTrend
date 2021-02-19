package com.ksw.movietrend.ui.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ksw.movietrend.R
import com.ksw.movietrend.adapter.UpcomingAdapter
import com.ksw.movietrend.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_upcoming.*
import kotlinx.android.synthetic.main.layout_loading.*

@AndroidEntryPoint
class UpcomingFragment : Fragment(R.layout.fragment_upcoming) {

    lateinit var upcomingAdapter: UpcomingAdapter
    private val upcomingViewModel by viewModels<UpcomingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upcomingAdapter = UpcomingAdapter()

        recyclerview_UpcomingMovie.layoutManager = LinearLayoutManager(requireContext())
        recyclerview_UpcomingMovie.adapter = upcomingAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        upcomingViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    showLoad(false)
                    upcomingAdapter.setMovies(it.data!!)
                }
                Status.LOADING -> {
                    showLoad(false)
                }
                Status.ERROR -> {
                    showLoad(true)
                    Snackbar.make(requireView(), "인터넷 연결을 해주세요.", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoad(isShow: Boolean) {
        if (isShow) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

}