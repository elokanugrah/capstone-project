package elok.dicoding.made.capstoneproject.ui.components.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import elok.dicoding.made.capstoneproject.MyApplication
import elok.dicoding.made.capstoneproject.databinding.ActivityDetailBinding
import elok.dicoding.made.capstoneproject.ui.ViewModelFactory
import elok.dicoding.made.core.R
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.ui.base.BaseActivity
import elok.dicoding.made.core.utils.ext.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>({ ActivityDetailBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: DetailViewModel by viewModels { factory }

    @ExperimentalCoroutinesApi
    override fun ActivityDetailBinding.onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this@DetailActivity)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val movieTv = intent.getParcelableExtra<MovieTv>(EXTRA_MOVIE_TV)
        movieTv?.let {
            viewModel.setSelectedItem(it)
        }
    }

    override fun observeViewModel() {
        observe(viewModel.movieTvItem) { binding.item = it }
        observe(viewModel.isFavorite, ::setFavoriteState)
    }

    private fun setFavoriteState(isFavorite: Boolean) {
        binding.fabFav.setOnClickListener {
            viewModel.setToFavorite(isFavorite)
        }
        binding.fabFav.setImageDrawable(
                ContextCompat.getDrawable(
                        this@DetailActivity,
                        if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border
                )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_MOVIE_TV = "key.EXTRA_MOVIE_TV"

        fun navigate(activity: Activity, movieTv: MovieTv) {
            Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_TV, movieTv)
            }.also {
                activity.startActivity(it)
            }
        }
    }
}