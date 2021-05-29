package elok.dicoding.made.capstoneproject.ui.components.moviedetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.core.view.size
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import elok.dicoding.made.capstoneproject.MyApplication
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.capstoneproject.databinding.FragmentMovieDetailBinding
import elok.dicoding.made.capstoneproject.ui.ViewModelFactory
import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.domain.model.GenreMovie
import elok.dicoding.made.core.domain.model.Movie
import elok.dicoding.made.core.domain.model.ProducationCompany
import elok.dicoding.made.core.ui.base.BaseFragment
import elok.dicoding.made.core.utils.ext.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>({ FragmentMovieDetailBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MovieDetailViewModel by viewModels { factory }
    private val productionCompanyAdapter by lazy { ProductionCompanyAdapter() }
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun FragmentMovieDetailBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            rvProductionCompany.adapter = productionCompanyAdapter
        }

        val movie = args.movie
        movie.let {
            viewModel.setSelectedItem(it)
        }
    }

    override fun observeViewModel() {
        observe(viewModel.movieItem) { binding?.movie = it }
        observe(viewModel.movieDetail, ::handleMovieDetails)
        observe(viewModel.movieGenres, ::handleMovieGenres)
        observe(viewModel.movieProductionCompanies, ::handleMovieProductionCompanies)
    }

    private fun handleMovieDetails(movie: Resource<Movie>) {
        Log.w("MOVIEDETAIL", "${movie.data}")
    }

    private fun handleMovieGenres(movieWithGenres: List<GenreMovie>) {
        // Need to change to flexbox
        binding?.apply {
            if (genreChipGroup.size == 0) {
                movieWithGenres.forEach {
                    val chip = layoutInflater.inflate(
                        R.layout.item_genre_chip_choice,
                        genreChipGroup,
                        false
                    ) as Chip
                    chip.text = it.name
                    chip.id = it.id.toInt()
                    genreChipGroup.addView(chip)
                }
            }
        }
    }

    private fun handleMovieProductionCompanies(productionCompanies: List<ProducationCompany>) {
        productionCompanyAdapter.submitList(productionCompanies)
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }
}