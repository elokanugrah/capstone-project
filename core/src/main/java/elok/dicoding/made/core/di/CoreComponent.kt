package elok.dicoding.made.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import elok.dicoding.made.core.domain.repository.IMovieRepository
import elok.dicoding.made.core.domain.repository.IMovieTvRepository
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideMovieTvRepository(): IMovieTvRepository
    fun provideMovieRepository(): IMovieRepository
}