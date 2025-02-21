package com.example.expertcapstone.presenter.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.expertcapstone.R
import com.example.expertcapstone.core.domain.model.GameDetail
import com.example.expertcapstone.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var gameDetail: GameDetail
    private var favoriteId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Game App"

        val id = intent.getIntExtra(EXTRA_ID, 0)
        detailViewModel.getDetailGame(id)

        //if the data not found in the database the default value will be 0 from the function
        detailViewModel.getFavoriteGameById(id)

        detailViewModel.favoriteGame.observe(this) { favoriteGame ->
            if (favoriteGame.name.isNotEmpty()) {
                setStatusFavorite(true)
                favoriteId = favoriteGame.id
                println("Favorite Game ID AFTER ADDED: $favoriteId")
                //ensuring the data initialized is from database
                gameDetail = favoriteGame
            } else {
                setStatusFavorite(false)
                favoriteId = favoriteGame.id
                println("Favorite Game ID: $favoriteId")
            }
        }

        detailViewModel.gameDetail.observe(this) { game ->
            setDetailData(game)
            //ensuring the data initialized is from API
            if (favoriteId == 0) {
                gameDetail = game
            }
        }

        binding.tvDetailFavorite.setOnClickListener {
            if (favoriteId == 0 && gameDetail != null) {
                detailViewModel.insertGame(gameDetail)
                showSnackBar("${gameDetail.name} added to wishlist")
            } else {
                detailViewModel.deleteGame(gameDetail)
                showSnackBar("${gameDetail.name} removed from wishlist")
            }
        }

        detailViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        detailViewModel.isError.observe(this) { isError ->
            showError(isError)
        }


    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.tvDetailFavorite.setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24),
                null,
                null,
                null
            )
            binding.tvDetailFavorite.text = getString(R.string.txt_remove_from_wishlist)
        } else {
            binding.tvDetailFavorite.setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24),
                null,
                null,
                null
            )
            binding.tvDetailFavorite.text = getString(R.string.txt_add_to_whislist)
        }
    }

    private fun setDetailData(gameDetail: GameDetail) {
        binding.apply {
            tvDetailTitle.text = gameDetail.name
            tvDetailReleased.text = getString(R.string.txt_released_date, gameDetail.released)
            tvDetailDescription.text = gameDetail.descriptionRaw
            tvDetailPublisher.text = gameDetail.publishers
            tvDetailWebsite.text = gameDetail.website
            tvDetailRating.text = gameDetail.rating.toString()
        }
        Glide.with(this)
            .load(gameDetail.backgroundImage)
            .into(binding.ivDetailImg)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(isError: Boolean) {
        binding.errorMessage.visibility = if (isError) View.VISIBLE else View.GONE
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}