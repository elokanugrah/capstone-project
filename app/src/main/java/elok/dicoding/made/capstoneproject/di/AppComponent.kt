package elok.dicoding.made.capstoneproject.di

import dagger.Component
import elok.dicoding.made.capstoneproject.ui.components.MainActivity
import elok.dicoding.made.capstoneproject.ui.components.detail.DetailActivity
import elok.dicoding.made.capstoneproject.ui.components.movie.MovieFragment
import elok.dicoding.made.capstoneproject.ui.components.moviedetail.MovieDetailFragment
import elok.dicoding.made.capstoneproject.ui.components.tv.TvFragment
import elok.dicoding.made.core.di.CoreComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: MovieDetailFragment)
    fun inject(fragment: TvFragment)
}