package elok.dicoding.made.favorites.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.paging.PagedList
import elok.dicoding.made.capstoneproject.ui.ViewModelFactory
import elok.dicoding.made.capstoneproject.ui.components.detail.DetailActivity
import elok.dicoding.made.core.di.CoreComponent
import elok.dicoding.made.core.di.DaggerCoreComponent
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.ui.base.BaseFragment
import elok.dicoding.made.core.utils.ext.gone
import elok.dicoding.made.core.utils.ext.observe
import elok.dicoding.made.core.utils.ext.shareMovieTv
import elok.dicoding.made.core.utils.ext.visible
import elok.dicoding.made.favorites.databinding.FragmentFavoriteMovieBinding
import elok.dicoding.made.favorites.di.DaggerFavoriteComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class FavoriteMovieFragment : BaseFragment<FragmentFavoriteMovieBinding>({ FragmentFavoriteMovieBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }
    private val viewModel: FavoriteViewModel by viewModels { factory }
    private val adapter by lazy { FavoriteMovieAdapter() }

    override fun FragmentFavoriteMovieBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.rvFavoriteMovie?.adapter = this@FavoriteMovieFragment.adapter
        adapter.listener = { _, _, item ->
            DetailActivity.navigate(requireActivity(), item)
        }
        adapter.shareListener = { requireActivity().shareMovieTv(it) }
    }

    override fun observeViewModel() {
        observe(viewModel.favoriteMovies, ::handleFavMovies)
    }

    private fun handleFavMovies(favMovies: PagedList<MovieTv>) {
        if (!favMovies.isNullOrEmpty()) {
            binding?.emptyFavorite?.root?.gone()
            binding?.rvFavoriteMovie?.visible()
            adapter.submitList(favMovies)
        } else {
            binding?.emptyFavorite?.root?.visible()
            binding?.rvFavoriteMovie?.gone()
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }

    override fun onDestroyView() {
        binding?.rvFavoriteMovie?.adapter = null
        super.onDestroyView()
    }
}