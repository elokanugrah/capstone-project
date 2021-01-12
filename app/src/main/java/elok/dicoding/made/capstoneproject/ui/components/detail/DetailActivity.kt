package elok.dicoding.made.capstoneproject.ui.components.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import elok.dicoding.made.capstoneproject.MyApplication
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.capstoneproject.databinding.ActivityDetailBinding
import elok.dicoding.made.capstoneproject.ui.ViewModelFactory
import elok.dicoding.made.core.R.drawable
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.ui.base.BaseActivity
import elok.dicoding.made.core.utils.ext.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.ref.WeakReference
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

    private fun setFavoriteState(isFavoriteBefore: Boolean) {
        binding.apply {
            fabFav.setOnClickListener {
                viewModel.setToFavorite(isFavoriteBefore)

                Snackbar.make(
                    coordinatorLayout,
                    getString(if (isFavoriteBefore) R.string.deleted_remove_favorite else R.string.added_to_favorite),
                    Snackbar.LENGTH_LONG
                )
                    .setBackgroundTint(
                        ContextCompat.getColor(
                            this@DetailActivity,
                            if (isFavoriteBefore) R.color.red else R.color.green
                        )
                    )
                    .show()
            }

            fabFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailActivity,
                    if (isFavoriteBefore) drawable.ic_favorite_filled else drawable.ic_favorite_border
                )
            )
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_MOVIE_TV = "key.EXTRA_MOVIE_TV"

        fun navigate(activity: Activity, movieTv: MovieTv) {
            val activityReference: WeakReference<Activity> = WeakReference(activity)
            val movieTvReference: WeakReference<MovieTv> = WeakReference(movieTv)

            Intent(activityReference.get(), DetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_TV, movieTvReference.get())
            }.also {
                activityReference.get()?.startActivity(it)
            }
        }
    }
}