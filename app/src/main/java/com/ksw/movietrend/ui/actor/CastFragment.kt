package com.ksw.movietrend.ui.actor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.tabs.TabLayoutMediator
import com.ksw.movietrend.R
import com.ksw.movietrend.glide.GlideApp
import kotlinx.android.synthetic.main.fragment_cast.*

class CastFragment : Fragment(R.layout.fragment_cast) {

    private val args: CastFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cast = args.cast

        iv_back.setOnClickListener {
            it.findNavController().popBackStack()
        }

        GlideApp.with(iv_Cast)
            .load("https://image.tmdb.org/t/p/original${cast.profilePath}")
            .placeholder(R.drawable.ic_user_placeholder)
            .error(R.drawable.ic_user_placeholder)
            .transform(CircleCrop())
            .into(iv_Cast)

        tv_Name.text = cast.name

        viewPager2.adapter = ViewPagerAdapter(this, cast.id)
        viewPager2.offscreenPageLimit = 2

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.about)
                }
                1 -> {
                    tab.text = getString(R.string.photo)
                }
                else -> {
                    tab.text = "movie"
                }
            }
        }.attach()

    }
}

class ViewPagerAdapter(fragment: Fragment, private val castId: Long) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AboutFragment.newInstance(castId)
            }
            1 -> {
                PhotoFragment.newInstance(castId)
            }
            else -> {
                MovieFragment.newInstance("1", "1")
            }
        }
    }

}