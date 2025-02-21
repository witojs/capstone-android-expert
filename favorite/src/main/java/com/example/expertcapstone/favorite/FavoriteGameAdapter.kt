package com.example.expertcapstone.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.expertcapstone.core.domain.model.GameDetail
import com.example.expertcapstone.favorite.databinding.ItemFavoriteGameBinding

class FavoriteGameAdapter :
    ListAdapter<GameDetail, FavoriteGameAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((GameDetail) -> Unit)? = null

    inner class ListViewHolder(private var binding: ItemFavoriteGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: GameDetail) {
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteGameAdapter.ListViewHolder {
        return ListViewHolder(
            ItemFavoriteGameBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteGameAdapter.ListViewHolder, position: Int) {
        val favoriteGame = getItem(position)
        holder.bind(favoriteGame)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GameDetail> =
            object : DiffUtil.ItemCallback<GameDetail>() {
                override fun areItemsTheSame(oldItem: GameDetail, newItem: GameDetail): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: GameDetail, newItem: GameDetail): Boolean {
                    return oldItem == newItem
                }
            }
    }
}