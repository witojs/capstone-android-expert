package com.example.expertcapstone.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expertcapstone.core.domain.model.GameDetail
import com.example.expertcapstone.di.FavoriteModuleDependency
import com.example.expertcapstone.favorite.databinding.ActivityFavoriteBinding
import com.example.expertcapstone.presenter.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteGameAdapter: FavoriteGameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependency::class.java
                )
            ).build().inject(this)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Game App"

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.layoutManager = layoutManager

        favoriteViewModel.favoriteGame.observe(this) { favoriteGames ->
            if (favoriteGames.isNullOrEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
            }
            updateData(favoriteGames)
        }
    }

    private fun updateData(favoriteGames: List<GameDetail>) {
        favoriteGameAdapter = FavoriteGameAdapter()
        favoriteGameAdapter.submitList(favoriteGames)
        favoriteGameAdapter.onItemClick = { selectedGame ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, selectedGame.id)
            startActivity(intent)
        }
        binding.rvFavorite.adapter = favoriteGameAdapter
    }
}