package com.example.expertcapstone.core.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.expertcapstone.core.R
import com.example.expertcapstone.core.databinding.ItemListGameBinding
import com.example.expertcapstone.core.domain.model.Game

class GameAdapter : ListAdapter<Game, GameAdapter.ListViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((Game) -> Unit)? = null

    inner class ListViewHolder(private var binding: ItemListGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.tvItemTitle.text = game.name
            binding.tvItemReleased.text = game.released
            binding.tvItemRating.text = itemView.context.getString(
                R.string.txt_rating,
                game.rating.toString(),
                game.ratingTop.toString()
            )
            Glide.with(itemView.context)
                .load(game.backgroundImage)
                .into(binding.ivItemImage)
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemListGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(game)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Game> =
            object : DiffUtil.ItemCallback<Game>() {
                override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                    return oldItem == newItem
                }
            }
    }
}