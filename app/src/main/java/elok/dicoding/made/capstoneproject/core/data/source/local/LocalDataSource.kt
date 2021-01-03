package elok.dicoding.made.capstoneproject.core.data.source.local

import androidx.paging.DataSource
import elok.dicoding.made.capstoneproject.core.data.source.local.entity.FavoriteMovieTvEntity
import elok.dicoding.made.capstoneproject.core.data.source.local.entity.MovieTvEntity
import elok.dicoding.made.capstoneproject.core.data.source.local.room.FavoriteMovieTvDao
import elok.dicoding.made.capstoneproject.core.data.source.local.room.MovieTvDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieTvDao: MovieTvDao, private val favoriteMovieTvDao: FavoriteMovieTvDao) {

    fun getMovieList(): Flow<List<MovieTvEntity>> = movieTvDao.getMovies()
    fun getTvShowList(): Flow<List<MovieTvEntity>> = movieTvDao.getTvShows()
    suspend fun insertMovieTv(movie: List<MovieTvEntity>) = movieTvDao.insertMovieTv(movie)
    fun setFavoriteMovieTv(movie: FavoriteMovieTvEntity, saved: Boolean) {
        if (!saved) favoriteMovieTvDao.insert(movie) else favoriteMovieTvDao.delete(movie)
    }
    fun getFavoriteMovies(): DataSource.Factory<Int, FavoriteMovieTvEntity> = favoriteMovieTvDao.getFavoriteMovies()
    fun getFavoriteTvShows(): DataSource.Factory<Int, FavoriteMovieTvEntity> = favoriteMovieTvDao.getFavoriteTvShows()
    suspend fun isFavorite(movieTv: FavoriteMovieTvEntity): Boolean = favoriteMovieTvDao.isExist(movieTv.id)
}