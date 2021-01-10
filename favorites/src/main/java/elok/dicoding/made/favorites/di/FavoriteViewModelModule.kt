package elok.dicoding.made.favorites.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import elok.dicoding.made.capstoneproject.di.ViewModelKey
import elok.dicoding.made.capstoneproject.ui.ViewModelFactory
import elok.dicoding.made.favorites.ui.FavoriteViewModel

@Suppress("unused")
@Module
abstract class FavoriteViewModelModule {

    @Binds
    abstract fun bindFavoriteViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}