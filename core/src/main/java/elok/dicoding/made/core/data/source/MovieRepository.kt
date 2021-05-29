package elok.dicoding.made.core.data.source

import elok.dicoding.made.core.data.NetworkBoundResource
import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.data.source.local.LocalMovieDataSource
import elok.dicoding.made.core.data.source.remote.RemoteMovieDataSource
import elok.dicoding.made.core.data.source.remote.network.ApiResponse
import elok.dicoding.made.core.data.source.remote.response.MovieResponse
import elok.dicoding.made.core.domain.model.GenreMovie
import elok.dicoding.made.core.domain.model.Movie
import elok.dicoding.made.core.domain.model.ProducationCompany
import elok.dicoding.made.core.domain.repository.IMovieRepository
import elok.dicoding.made.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource,
    private val localDataSource: LocalMovieDataSource
) : IMovieRepository {
    override fun getMovieList(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovieList().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieList()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponseToEntities(data)
                localDataSource.insertMovieList(movieList)
            }
        }.asFlow()

    override fun searchMovieList(query: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.searchMovieList(query).first()) {
            is ApiResponse.Success -> {
                val response = DataMapper.mapMovieResponseToEntities(apiResponse.data)
                emit(Resource.Success(DataMapper.mapMovieEntitiesToDomain(response)))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error<List<Movie>>("No result found, please try different keyword"))
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error<List<Movie>>(
                        apiResponse.errorMessage
                    )
                )
            }
        }
    }

    override fun getMovieDetails(movie: Movie): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieDetail(movie.id).map {
                    DataMapper.mapMovieEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.homepage == null

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieDetails(movie.id)

            override suspend fun saveCallResult(data: MovieResponse) {
                val movieEntity = DataMapper.mapMovieResponseToEntity(data)
                val movieGenres = DataMapper.mapMovieGenreResponseToEntities(data)
                val productionCompanies = DataMapper.mapProductionCompanyResponseToEntities(data)


                localDataSource.insertGenreMovieList(movieGenres)
                localDataSource.insertProductionCompanyList(productionCompanies)
                localDataSource.updateMovieDetail(movieEntity)
            }
        }.asFlow()

    override fun getMovieGenres(movie: Movie): Flow<List<GenreMovie>> {
        val movieId = movie.id
        return localDataSource.getMovieWithGenreList(movieId).map {
            DataMapper.mapMovieWithGenreEntityToDomain(it)
        }
    }

    override fun getMovieProductionCompanies(movie: Movie): Flow<List<ProducationCompany>> {
        val movieId = movie.id
        return localDataSource.getMovieWithProductionCompanyList(movieId).map {
            DataMapper.mapMovieWithProductionCompanyEntityToDomain(it)
        }
    }
}

