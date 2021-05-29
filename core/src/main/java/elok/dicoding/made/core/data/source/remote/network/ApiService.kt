package elok.dicoding.made.core.data.source.remote.network

import elok.dicoding.made.core.BuildConfig
import elok.dicoding.made.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/now_playing")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ): ListMovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ): MovieResponse

    @GET("tv/popular")
    suspend fun getTvPopularList(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ): ListTvResponse

    @GET("genre/tv/list")
    suspend fun getGenreTvList(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ): ListGenreTvResponse

    @GET("search/movie")
    suspend fun getSearchMovieList(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY,
        @Query("query") query: String?
    ): ListMovieResponse

    @GET("search/tv")
    suspend fun getSearchTvList(
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY,
        @Query("query") query: String?
    ): ListTvResponse
}
