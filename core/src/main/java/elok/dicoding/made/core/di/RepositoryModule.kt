package elok.dicoding.made.core.di

import dagger.Binds
import dagger.Module
import elok.dicoding.made.core.data.source.TMDBRepository
import elok.dicoding.made.core.domain.repository.IMovieTvRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

  @Suppress("unused")
  @Binds
  abstract fun provideRepository(tmdbRepository: TMDBRepository): IMovieTvRepository
}