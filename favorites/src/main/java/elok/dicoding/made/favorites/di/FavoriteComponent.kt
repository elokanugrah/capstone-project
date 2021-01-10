package elok.dicoding.made.favorites.di

import dagger.Component
import elok.dicoding.made.capstoneproject.di.AppModule
import elok.dicoding.made.core.di.CoreComponent
import elok.dicoding.made.favorites.ui.FavoriteFragment
import elok.dicoding.made.favorites.ui.FavoriteMovieFragment
import elok.dicoding.made.favorites.ui.FavoriteTvFragment

@FavoriteAppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, FavoriteViewModelModule::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvFragment)
}