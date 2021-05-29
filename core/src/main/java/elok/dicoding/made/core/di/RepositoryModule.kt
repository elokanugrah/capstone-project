package elok.dicoding.made.core.di

import dagger.Binds
import dagger.Module
import elok.dicoding.made.core.data.source.MovieRepository
import elok.dicoding.made.core.data.source.TMDBRepository
import elok.dicoding.made.core.domain.repository.IMovieRepository
import elok.dicoding.made.core.domain.repository.IMovieTvRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Suppress("unused")
    @Binds
    abstract fun provideMovieTvRepository(tmdbRepository: TMDBRepository): IMovieTvRepository

    @Suppress("unused")
    @Binds
    abstract fun provideMovieRepository(movieRepository: MovieRepository): IMovieRepository
}