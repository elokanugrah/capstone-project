package elok.dicoding.made.favorites.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.core.di.CoreComponent
import elok.dicoding.made.core.di.DaggerCoreComponent
import elok.dicoding.made.core.ui.base.BaseFragment
import elok.dicoding.made.favorites.databinding.FragmentFavoriteBinding
import elok.dicoding.made.favorites.di.DaggerFavoriteComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.math.abs

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>({ FragmentFavoriteBinding.inflate(it) }) {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }

    override fun FragmentFavoriteBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.appbar?.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            binding?.appbar?.isSelected = abs(verticalOffset) - appBarLayout.totalScrollRange == 0
        })
        initFavoritePagerAdapter()
        initTabLayout()
    }

    override fun observeViewModel() {}

    private fun initFavoritePagerAdapter() {
        val pagerAdapter = FavoritePagerAdapter(childFragmentManager, lifecycle)
        binding?.apply {
            viewPager.apply {
                offscreenPageLimit = 2
                adapter = pagerAdapter
            }
        }
    }

    private fun initTabLayout() {
        binding?.apply {
            TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
                tab.text = when (pos) {
                    0 -> getString(R.string.movie)
                    else -> getString(R.string.tv_show)
                }
            }.attach()
        }
    }

    private class FavoritePagerAdapter(
            fm: FragmentManager,
            lifecycle: Lifecycle
    ) : FragmentStateAdapter(fm, lifecycle) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> FavoriteMovieFragment()
            else -> FavoriteTvFragment()
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }
}