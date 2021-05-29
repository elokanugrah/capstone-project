package elok.dicoding.made.core.data.source.remote

import android.util.Log
import elok.dicoding.made.core.data.source.remote.network.ApiResponse
import elok.dicoding.made.core.data.source.remote.network.ApiService
import elok.dicoding.made.core.data.source.remote.response.*
import elok.dicoding.made.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovies(): Flow<ApiResponse<List<MovieTvResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray?.isNotEmpty() ?: return@flow){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                EspressoIdlingResource.decrement()
                Log.e("RemoteDataSourceMovies", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(): Flow<ApiResponse<List<MovieTvResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getTvShows()
                val dataArray = response.results
                if (dataArray?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                EspressoIdlingResource.decrement()
                Log.e("RemoteDataSourceTvShows", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchMovies(query: String): Flow<ApiResponse<List<MovieTvResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getSearchMovies(query = query)
                val dataArray = response.results
                if (dataArray?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                EspressoIdlingResource.decrement()
                Log.e("RemoteDataSourceMovies", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchTvShows(query: String): Flow<ApiResponse<List<MovieTvResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getSearchTvShows(query = query)
                val dataArray = response.results
                if (dataArray?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                EspressoIdlingResource.decrement()
                Log.e("RemoteDataSourceTvShows", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchTvList(query: String): Flow<ApiResponse<List<TvResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getSearchTvList(query = query)
                val dataArray = response.results
                if (dataArray?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                EspressoIdlingResource.decrement()
                Log.e("RemoteDataSourceTvShows", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvPopularList(): Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            try {
                val response = apiService.getTvPopularList()
                val dataArray = response.results
                if (dataArray?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteTvPopular", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGenreTvList(): Flow<ApiResponse<List<GenreTvResponse>>> {
        return flow {
            try {
                val response = apiService.getGenreTvList()
                val dataArray = response.genres
                if (dataArray?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(response.genres))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteGenreTv", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}