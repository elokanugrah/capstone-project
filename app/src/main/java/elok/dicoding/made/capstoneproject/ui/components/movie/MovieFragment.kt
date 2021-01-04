package elok.dicoding.made.capstoneproject.ui.components.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import elok.dicoding.made.capstoneproject.MyApplication
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.capstoneproject.databinding.FragmentMovieBinding
import elok.dicoding.made.capstoneproject.ui.ViewModelFactory
import elok.dicoding.made.capstoneproject.ui.components.detail.DetailActivity
import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.ui.base.BaseFragment
import elok.dicoding.made.core.utils.ext.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class MovieFragment : BaseFragment<FragmentMovieBinding>({ FragmentMovieBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MovieViewModel by viewModels { factory }
    private val adapter by lazy { MovieAdapter() }

    override fun FragmentMovieBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.rvMovie?.adapter = this@MovieFragment.adapter
        binding?.rvMovie?.hasFixedSize()
        binding?.rvMovie?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding?.appbar?.isSelected = recyclerView.canScrollVertically(-1)
            }
        })
        adapter.lifecycleOwner = this@MovieFragment
        adapter.viewModel = this@MovieFragment.viewModel
        adapter.listener = { _, _, item ->
            DetailActivity.navigate(requireActivity(), item)
        }
        adapter.favoriteListener = { item, isFavorite ->
            viewModel.setToFavorite(item, isFavorite)
        }
        adapter.shareListener = { requireActivity().shareMovieTv(it) }
    }

    override fun observeViewModel() {
        observe(viewModel.movies, ::handleMovies)
    }

    private fun handleMovies(movies: Resource<List<MovieTv>>) {
        binding?.apply {
            when (movies) {
                is Resource.Loading -> loading.root.visible()
                is Resource.Success -> {
                    loading.root.gone()
                    adapter.submitList(movies.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (movies.data.isNullOrEmpty()) {
                        error.root.visible()
                        error.message.text =
                            movies.message ?: getString(R.string.default_error_message)
                    } else {
                        requireContext().showToast(getString(R.string.default_error_message))
                        adapter.submitList(movies.data)
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }
}