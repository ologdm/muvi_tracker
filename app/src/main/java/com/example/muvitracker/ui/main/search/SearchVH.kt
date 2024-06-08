package com.example.muvitracker.ui.main.search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.muvitracker.R
import com.example.muvitracker.databinding.VhSearchBinding
import com.example.muvitracker.data.dto.SearchDto
import com.example.muvitracker.utils.firstDecimalApproxToString

class SearchVH(
    val binding: VhSearchBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchDto) {

        binding.run {
            typeObject.text = item.type
            score.text = item.score.firstDecimalApproxToString()

            if (item.type == "movie") { // mostra solo i movie, senza
                title.text = "${item.movie?.title} ${item.movie?.year.toString()}"

                Glide.with(root.context)
                    .load(item.movie?.imageUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.glide_placeholder_search)
                    .error(R.drawable.glide_placeholder_search)
                    .into(image)
            }
        }
    }

}

