package elok.dicoding.made.capstoneproject.di

import dagger.Binds
import dagger.Module
import elok.dicoding.made.capstoneproject.core.domain.usecase.MovieTvInteractor
import elok.dicoding.made.capstoneproject.core.domain.usecase.MovieTvUseCase

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieTvInteractor): MovieTvUseCase
}