package elok.dicoding.made.core.data.source.local

import androidx.paging.DataSource
import elok.dicoding.made.core.data.source.local.entity.FavoriteMovieTvEntity
import elok.dicoding.made.core.data.source.local.entity.GenreTvEntity
import elok.dicoding.made.core.data.source.local.entity.MovieTvEntity
import elok.dicoding.made.core.data.source.local.entity.TvEntity
import elok.dicoding.made.core.data.source.local.entity.relation.TvGenreCrossRef
import elok.dicoding.made.core.data.source.local.entity.relation.TvWithGenres
import elok.dicoding.made.core.data.source.local.room.FavoriteMovieTvDao
import elok.dicoding.made.core.data.source.local.room.MovieTvDao
import elok.dicoding.made.core.data.source.local.room.TvDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val movieTvDao: MovieTvDao,
    private val favoriteMovieTvDao: FavoriteMovieTvDao,
    private val tvDao: TvDao
) {
    fun getMovieList(): Flow<List<MovieTvEntity>> = movieTvDao.getMovies()
    fun getTvShowList(): Flow<List<MovieTvEntity>> = movieTvDao.getTvShows()
    suspend fun insertMovieTv(movie: List<MovieTvEntity>) = movieTvDao.insertMovieTv(movie)
    fun setFavoriteMovieTv(movie: FavoriteMovieTvEntity, saved: Boolean) {
        if (!saved) favoriteMovieTvDao.insert(movie) else favoriteMovieTvDao.delete(movie)
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, FavoriteMovieTvEntity> =
        favoriteMovieTvDao.getFavoriteMovies()

    fun getFavoriteTvShows(): DataSource.Factory<Int, FavoriteMovieTvEntity> =
        favoriteMovieTvDao.getFavoriteTvShows()

    suspend fun isFavorite(movieTv: FavoriteMovieTvEntity): Boolean =
        favoriteMovieTvDao.isExist(movieTv.id)

    suspend fun insertTvList(tv: List<TvEntity>) = tvDao.insertTvList(tv)
    suspend fun insertGenreTvList(genreTv: List<GenreTvEntity>) = tvDao.insertGenreTvList(genreTv)
    suspend fun insertGenreTvCrossRefList(genreTv: List<TvGenreCrossRef>) =
        tvDao.insertTvGenreCrossRef(genreTv)

    fun getTvList(): Flow<List<TvEntity>> = tvDao.getTvList()
    fun getGenreTvList(): Flow<List<GenreTvEntity>> = tvDao.getGenreTvList()
    fun getTvWithGenresList(tvId: Long): Flow<TvWithGenres> = tvDao.getTvsWithGenres(tvId)
}