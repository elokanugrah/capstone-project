package elok.dicoding.made.capstoneproject.di

import dagger.Binds
import dagger.Module
import elok.dicoding.made.core.domain.usecase.MovieTvInteractor
import elok.dicoding.made.core.domain.usecase.MovieTvUseCase

@Module
abstract class AppModule {

    @Suppress("unused")
    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieTvInteractor): MovieTvUseCase
}