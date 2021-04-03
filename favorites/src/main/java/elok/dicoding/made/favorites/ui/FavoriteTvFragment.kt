package elok.dicoding.made.favorites.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import elok.dicoding.made.capstoneproject.ui.ViewModelFactory
import elok.dicoding.made.core.di.CoreComponent
import elok.dicoding.made.core.di.DaggerCoreComponent
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.ui.base.BaseFragment
import elok.dicoding.made.core.utils.ext.gone
import elok.dicoding.made.core.utils.ext.observe
import elok.dicoding.made.core.utils.ext.shareMovieTv
import elok.dicoding.made.core.utils.ext.visible
import elok.dicoding.made.favorites.databinding.FragmentFavoriteTvBinding
import elok.dicoding.made.favorites.di.DaggerFavoriteComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class FavoriteTvFragment : BaseFragment<FragmentFavoriteTvBinding>({ FragmentFavoriteTvBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }
    private val viewModel: FavoriteViewModel by viewModels { factory }
    private val adapter by lazy { FavoriteMovieAdapter() }

    override fun FragmentFavoriteTvBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.rvFavoriteTv?.adapter = this@FavoriteTvFragment.adapter
        adapter.listener = { _, _, item ->
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(item)
            )
        }
        adapter.shareListener = { requireActivity().shareMovieTv(it) }
    }

    override fun observeViewModel() {
        observe(viewModel.favoriteTvShows, ::handleFavTvShows)
    }

    private fun handleFavTvShows(favMovies: PagedList<MovieTv>) {
        if (!favMovies.isNullOrEmpty()) {
            binding?.emptyFavorite?.root?.gone()
            binding?.rvFavoriteTv?.visible()
            adapter.submitList(favMovies)
        } else {
            binding?.emptyFavorite?.root?.visible()
            binding?.rvFavoriteTv?.gone()
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }

    override fun onDestroyView() {
        binding?.rvFavoriteTv?.adapter = null
        super.onDestroyView()
    }
}