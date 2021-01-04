package elok.dicoding.made.core.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import elok.dicoding.made.core.data.NetworkBoundResource
import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.data.source.local.LocalDataSource
import elok.dicoding.made.core.data.source.remote.RemoteDataSource
import elok.dicoding.made.core.data.source.remote.network.ApiResponse
import elok.dicoding.made.core.data.source.remote.response.MovieTvResponse
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.domain.repository.IMovieTvRepository
import elok.dicoding.made.core.utils.AppExecutors
import elok.dicoding.made.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TMDBRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieTvRepository {

    override fun getMovies(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<MovieTvResponse>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> {
                return localDataSource.getMovieList().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieTvResponse>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MovieTvResponse>) {
                val tourismList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovieTv(tourismList)
            }
        }.asFlow()

    override fun getTvShows(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<MovieTvResponse>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> {
                return localDataSource.getTvShowList().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieTvResponse>>> =
                remoteDataSource.getTvShows()

            override suspend fun saveCallResult(data: List<MovieTvResponse>) {
                val tourismList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovieTv(tourismList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): LiveData<PagedList<MovieTv>> {
        val favMovies = localDataSource.getFavoriteMovies().map {
            DataMapper.mapFavoriteEntityToDomain(it)
        }

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(favMovies, config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<MovieTv>> {
        val favTvShows = localDataSource.getFavoriteTvShows().map {
            DataMapper.mapFavoriteEntityToDomain(it)
        }

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(favTvShows, config).build()
    }

    override fun setFavoriteMovieTv(movieTv: MovieTv, saved: Boolean) {
        val movieTvEntity = DataMapper.mapDomainToFavoriteEntity(movieTv)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovieTv(movieTvEntity, saved) }
    }

    override fun isFavorite(movieTv: MovieTv): Flow<Boolean> {
        val movieTvEntity = DataMapper.mapDomainToFavoriteEntity(movieTv)
        return flow {
            emit(localDataSource.isFavorite(movieTvEntity))
        }
    }
}

