package elok.dicoding.made.core.data.source.remote.network

import elok.dicoding.made.core.BuildConfig
import elok.dicoding.made.core.data.source.remote.response.ListMovieTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ): ListMovieTvResponse

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ): ListMovieTvResponse

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY,
        @Query("query") query: String?
    ): ListMovieTvResponse

    @GET("search/tv")
    suspend fun getSearchTvShows(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY,
        @Query("query") query: String?
    ): ListMovieTvResponse
}
