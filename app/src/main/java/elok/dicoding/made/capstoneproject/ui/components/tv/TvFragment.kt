package elok.dicoding.made.capstoneproject.ui.components.tv

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.capstoneproject.core.data.Resource
import elok.dicoding.made.capstoneproject.core.domain.model.MovieTv
import elok.dicoding.made.capstoneproject.core.utils.ext.*
import elok.dicoding.made.capstoneproject.databinding.FragmentTvBinding
import elok.dicoding.made.capstoneproject.ui.ViewModelFactory
import elok.dicoding.made.capstoneproject.ui.base.BaseFragment
import elok.dicoding.made.capstoneproject.ui.components.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class TvFragment : BaseFragment<FragmentTvBinding>({ FragmentTvBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: TvViewModel by viewModels { factory }
    private val adapter by lazy { TvAdapter() }

    override fun FragmentTvBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.rvTv?.adapter = this@TvFragment.adapter
        binding?.rvTv?.hasFixedSize()
        binding?.rvTv?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding?.appbar?.isSelected = recyclerView.canScrollVertically(-1)
            }
        })
        adapter.lifecycleOwner = this@TvFragment
        adapter.viewModel = this@TvFragment.viewModel
        adapter.listener = { _, _, item ->
            DetailActivity.navigate(requireActivity(), item)
        }
        adapter.favoriteListener = { item, isFavorite ->
            viewModel.setToFavorite(item, isFavorite)
        }
        adapter.shareListener = { requireActivity().shareMovieTv(it) }
    }

    override fun observeViewModel() {
        observe(viewModel.tvShows, ::handleTvShows)
    }

    private fun handleTvShows(tvShows: Resource<List<MovieTv>>) {
        binding?.apply {
            when (tvShows) {
                is Resource.Loading -> loading.root.visible()
                is Resource.Success -> {
                    loading.root.gone()
                    adapter.submitList(tvShows.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (tvShows.data.isNullOrEmpty()) {
                        error.root.visible()
                        error.message.text =
                            tvShows.message ?: getString(R.string.default_error_message)
                    } else {
                        requireContext().showToast(getString(R.string.default_error_message))
                        adapter.submitList(tvShows.data)
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}