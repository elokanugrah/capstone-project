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
class RemoteMovieDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieList(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieList()
                val dataArray = response.results
                if (dataArray?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteMovieList", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchMovieList(query: String): Flow<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getSearchMovieList(query = query)
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

    suspend fun getMovieDetails(movieId: Long): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(id = movieId)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteMovieDetail", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}