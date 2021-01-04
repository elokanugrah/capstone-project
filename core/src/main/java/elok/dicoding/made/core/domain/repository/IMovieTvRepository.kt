package elok.dicoding.made.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.domain.model.MovieTv
import kotlinx.coroutines.flow.Flow

interface IMovieTvRepository {
    fun getMovies(): Flow<Resource<List<MovieTv>>>
    fun getTvShows(): Flow<Resource<List<MovieTv>>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieTv>>
    fun getFavoriteTvShows(): LiveData<PagedList<MovieTv>>
    fun setFavoriteMovieTv(movieTv: MovieTv, saved: Boolean)
    fun isFavorite(movieTv: MovieTv): Flow<Boolean>
}