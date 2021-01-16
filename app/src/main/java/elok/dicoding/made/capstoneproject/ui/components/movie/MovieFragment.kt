package elok.dicoding.made.capstoneproject.ui.components.movie

import android.content.Context
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieFragment : BaseFragment<FragmentMovieBinding>({ FragmentMovieBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MovieViewModel by viewModels { factory }
    private val movieAdapter by lazy { MovieAdapter() }

    override fun FragmentMovieBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.apply {
            rvMovie.adapter = movieAdapter
            rvMovie.hasFixedSize()
            rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    appbar.isSelected = recyclerView.canScrollVertically(-1)
                }
            })
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.queryChannel.send(p0.toString())
                    }
                    return true
                }
            })
        }
        movieAdapter.listener = { _, _, item ->
            DetailActivity.navigate(requireActivity(), item)
        }
        movieAdapter.shareListener = { requireActivity().shareMovieTv(it) }
    }

    override fun observeViewModel() {
        observe(viewModel.movies, ::handleMovies)
        observe(viewModel.search) { searchResult ->
            observe(searchResult?.asLiveData(), ::handleSearch)
        }
    }

    private fun handleMovies(movies: Resource<List<MovieTv>>) {
        binding?.apply {
            when (movies) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    loading.root.visible()
                }
                is Resource.Success -> {
                    loading.root.gone()
                    errorLayout.gone()
                    movieAdapter.submitList(movies.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (movies.data.isNullOrEmpty()) {
                        errorLayout.visible()
                        error.message.text =
                            movies.message ?: getString(R.string.default_error_message)
                    } else {
                        requireContext().showToast(getString(R.string.default_error_message))
                        movieAdapter.submitList(movies.data)
                    }
                }
            }
        }
    }

    private fun handleSearch(movies: Resource<List<MovieTv>>) {
        binding?.apply {
            when (movies) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    loading.root.visible()
                }
                is Resource.Success -> {
                    loading.root.gone()
                    errorLayout.gone()
                    movieAdapter.submitList(movies.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (movies.data.isNullOrEmpty()) {
                        errorLayout.visible()
                        error.message.text =
                            movies.message ?: getString(R.string.default_error_message)
                    } else {
                        requireContext().showToast(getString(R.string.default_error_message))
                        movieAdapter.submitList(movies.data)
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

    override fun onDestroyView() {
        binding?.rvMovie?.adapter = null
        super.onDestroyView()
    }
}