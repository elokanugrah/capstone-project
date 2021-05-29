package elok.dicoding.made.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.data.source.local.entity.relation.TvWithGenres
import elok.dicoding.made.core.domain.model.GenreTv
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface MovieTvUseCase {
    fun getMovies(): Flow<Resource<List<MovieTv>>>
    fun getTvShows(): Flow<Resource<List<MovieTv>>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieTv>>
    fun getFavoriteTvShows(): LiveData<PagedList<MovieTv>>
    fun searchMovies(query: String): Flow<Resource<List<MovieTv>>>
    fun searchTvShows(query: String): Flow<Resource<List<MovieTv>>>
    fun setFavoriteMovieTv(movieTv: MovieTv, saved: Boolean)
    fun isFavorite(movieTv: MovieTv): Flow<Boolean>
    fun getTvList(): Flow<Resource<List<Tv>>>
    fun getGenreTvList(): Flow<Resource<List<GenreTv>>>
    fun getTvGenres(tv: Tv): Flow<TvWithGenres>
    fun searchTvList(query: String): Flow<Resource<List<Tv>>>
}