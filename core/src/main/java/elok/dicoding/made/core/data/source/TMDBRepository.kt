package elok.dicoding.made.core.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import elok.dicoding.made.core.data.NetworkBoundResource
import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.data.source.local.LocalDataSource
import elok.dicoding.made.core.data.source.local.entity.relation.TvWithGenres
import elok.dicoding.made.core.data.source.remote.RemoteDataSource
import elok.dicoding.made.core.data.source.remote.network.ApiResponse
import elok.dicoding.made.core.data.source.remote.response.GenreTvResponse
import elok.dicoding.made.core.data.source.remote.response.MovieTvResponse
import elok.dicoding.made.core.data.source.remote.response.TvResponse
import elok.dicoding.made.core.domain.model.GenreTv
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.domain.model.Tv
import elok.dicoding.made.core.domain.repository.IMovieTvRepository
import elok.dicoding.made.core.utils.AppExecutors
import elok.dicoding.made.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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

    override fun searchMovies(query: String): Flow<Resource<List<MovieTv>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.searchMovies(query).first()) {
            is ApiResponse.Success -> {
                val response = DataMapper.mapResponseToEntities(apiResponse.data)
                emit(Resource.Success(DataMapper.mapEntitiesToDomain(response)))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error<List<MovieTv>>("No result found, please try different keyword"))
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<List<MovieTv>>(
                        apiResponse.errorMessage
                    )
                )
            }
        }
    }

    override fun searchTvShows(query: String): Flow<Resource<List<MovieTv>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.searchTvShows(query).first()) {
            is ApiResponse.Success -> {
                val response = DataMapper.mapResponseToEntities(apiResponse.data)
                emit(Resource.Success(DataMapper.mapEntitiesToDomain(response)))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error<List<MovieTv>>("No result found, please try different keyword"))
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<List<MovieTv>>(
                        apiResponse.errorMessage
                    )
                )
            }
        }
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

    override fun getTvList(): Flow<Resource<List<Tv>>> =
        object : NetworkBoundResource<List<Tv>, List<TvResponse>>() {
            override fun loadFromDB(): Flow<List<Tv>> {
                return localDataSource.getTvList().map {
                    DataMapper.mapTvEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> =
                remoteDataSource.getTvPopularList()

            override suspend fun saveCallResult(data: List<TvResponse>) {
                val tvList = DataMapper.mapTvResponseToEntities(data)
                val genreTvCrossRefList = DataMapper.mapTvGenreToCrossRef(data)
                localDataSource.insertTvList(tvList)
                localDataSource.insertGenreTvCrossRefList(genreTvCrossRefList)
            }
        }.asFlow()

    override fun getGenreTvList(): Flow<Resource<List<GenreTv>>> =
        object : NetworkBoundResource<List<GenreTv>, List<GenreTvResponse>>() {
            override fun loadFromDB(): Flow<List<GenreTv>> {
                return localDataSource.getGenreTvList().map {
                    DataMapper.mapGenreTvEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<GenreTv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GenreTvResponse>>> =
                remoteDataSource.getGenreTvList()

            override suspend fun saveCallResult(data: List<GenreTvResponse>) {
                val genreTvList = DataMapper.mapGenreTvResponseToEntities(data)
                localDataSource.insertGenreTvList(genreTvList)
            }
        }.asFlow()

    override fun getTvGenres(tv: Tv): Flow<TvWithGenres> {
        val tvId = tv.id
        return localDataSource.getTvWithGenresList(tvId)
    }

    override fun searchTvList(query: String): Flow<Resource<List<Tv>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.searchTvList(query).first()) {
            is ApiResponse.Success -> {
                val response = DataMapper.mapTvResponseToEntities(apiResponse.data)
                emit(Resource.Success(DataMapper.mapTvEntitiesToDomain(response)))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error<List<Tv>>("No result found, please try different keyword"))
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<List<Tv>>(
                        apiResponse.errorMessage
                    )
                )
            }
        }
    }
}

