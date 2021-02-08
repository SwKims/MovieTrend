package com.ksw.movietrend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ksw.movietrend.R
import com.ksw.movietrend.glide.GlideApp
import com.ksw.movietrend.model.Cast
import kotlinx.android.synthetic.main.item_cast.view.*

/**
 * Created by KSW on 2021-02-08
 */
class CastAdapter : ListAdapter<Cast, CastAdapter.MyViewHolder>(comparator) {

    class MyViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(cast: Cast) {
            itemView.apply {
                GlideApp.with(iv_castActor)
                    .load("https://image.tmdb.org/t/p/original${cast.profilePath}")
                    .placeholder(R.drawable.ic_user_placeholder)
                    .error(R.drawable.ic_user_placeholder)
                    .transform(CircleCrop())
                    .into(iv_castActor)

                tv_actorName.text = cast.name
            }

        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemView = layoutInflater.inflate(R.layout.item_cast, parent, false)
                return MyViewHolder(itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cast = getItem(position)
        holder.bind(cast)
    }

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<Cast>() {
            override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
                return oldItem == newItem
            }

        }
    }

}