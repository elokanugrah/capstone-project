package elok.dicoding.made.capstoneproject.di

import dagger.Binds
import dagger.Module
import elok.dicoding.made.core.domain.usecase.MovieTvInteractor
import elok.dicoding.made.core.domain.usecase.MovieTvUseCase
import elok.dicoding.made.core.domain.usecase.movie.MovieInteractor
import elok.dicoding.made.core.domain.usecase.movie.MovieUseCase

@Module
abstract class AppModule {

    @Suppress("unused")
    @Binds
    abstract fun provideMovieTvUseCase(movieTvInteractor: MovieTvInteractor): MovieTvUseCase

    @Suppress("unused")
    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase
}