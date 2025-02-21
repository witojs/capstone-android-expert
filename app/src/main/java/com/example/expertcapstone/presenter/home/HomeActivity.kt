package com.example.expertcapstone.presenter.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expertcapstone.core.adapter.GameAdapter
import com.example.expertcapstone.core.domain.model.Game
import com.example.expertcapstone.databinding.ActivityHomeBinding
import com.example.expertcapstone.presenter.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var gameAdapter: GameAdapter

    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Game App"

        val layoutManager = LinearLayoutManager(this)
        binding.rvGame.layoutManager = layoutManager

        homeViewModel.games.observe(this) { games ->
            updateData(games)
        }

        homeViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        homeViewModel.isError.observe(this) { isError ->
            showError(isError)
        }
    }

    private fun updateData(games: List<Game>) {
        gameAdapter = GameAdapter()
        gameAdapter.submitList(games)
        gameAdapter.onItemClick = { selectedGame ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, selectedGame.id)
            startActivity(intent)
        }
        binding.rvGame.adapter = gameAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(isError: Boolean) {
        binding.errorMessage.visibility = if (isError) View.VISIBLE else View.GONE
    }
}