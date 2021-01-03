package elok.dicoding.made.capstoneproject.core.di

import dagger.Binds
import dagger.Module
import elok.dicoding.made.capstoneproject.core.data.source.TMDBRepository
import elok.dicoding.made.capstoneproject.core.domain.repository.IMovieTvRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

  @Binds
  abstract fun provideRepository(tmdbRepository: TMDBRepository): IMovieTvRepository
}