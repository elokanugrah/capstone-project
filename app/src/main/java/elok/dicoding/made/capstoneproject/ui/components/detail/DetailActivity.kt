package elok.dicoding.made.capstoneproject.ui.components.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import elok.dicoding.made.capstoneproject.MyApplication
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.capstoneproject.databinding.ActivityDetailBinding
import elok.dicoding.made.capstoneproject.ui.ViewModelFactory
import elok.dicoding.made.core.R.drawable
import elok.dicoding.made.core.data.source.local.entity.relation.TvWithGenres
import elok.dicoding.made.core.ui.base.BaseActivity
import elok.dicoding.made.core.utils.ext.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>({ ActivityDetailBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: DetailViewModel by viewModels { factory }
    private val args: DetailActivityArgs by navArgs()

    @ExperimentalCoroutinesApi
    override fun ActivityDetailBinding.onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this@DetailActivity)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val movieTv = args.item
        movieTv?.let {
            viewModel.setSelectedItem(it)
        }
        val tv = args.tv
        tv?.let {
            viewModel.setSelectedItem(it)
        }
    }

    override fun observeViewModel() {
        observe(viewModel.movieTvItem) { binding.item = it }
        observe(viewModel.tvItem) { binding.tv = it }
//        observe(viewModel.isFavorite, ::setFavoriteState)
        observe(viewModel.tvGenres, ::handleTvGenres)
    }

    private fun handleTvGenres(tvWithGenres: TvWithGenres) {
        binding.apply {
            tvWithGenres.genresTv.forEachIndexed { _, genreTvEntity ->
                val chip = layoutInflater.inflate(
                    R.layout.item_genre_chip_choice,
                    genreChipGroup,
                    false
                ) as Chip
                chip.text = genreTvEntity.name
                chip.id = genreTvEntity.genreTvId.toInt()
                genreChipGroup.addView(chip)
            }
        }
    }

    private fun setFavoriteState(isFavoriteBefore: Boolean) {
        binding.apply {
            fabFav.setOnClickListener {
//                viewModel.setToFavorite(isFavoriteBefore)

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
}