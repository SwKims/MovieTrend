package com.ksw.movietrend.ui.trending

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ksw.movietrend.R
import com.ksw.movietrend.adapter.NowPlayingAdapter
import com.ksw.movietrend.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_trending.*
import kotlinx.android.synthetic.main.layout_loading.*


@AndroidEntryPoint
class TrendingFragment : Fragment(R.layout.fragment_trending) {

    lateinit var nowPlayingAdapter: NowPlayingAdapter
    private val nowPlayingViewModel by viewModels<TrendingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nowPlayingAdapter = NowPlayingAdapter()

        recyclerview_nowPlayingMovie.layoutManager = LinearLayoutManager(requireContext())
        recyclerview_nowPlayingMovie.adapter = nowPlayingAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        nowPlayingViewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    showLoad(false)
                    nowPlayingAdapter.setMovies(it.data!!)
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