package elok.dicoding.made.core.data.source.local

import elok.dicoding.made.core.data.source.local.entity.GenreMovieEntity
import elok.dicoding.made.core.data.source.local.entity.MovieEntity
import elok.dicoding.made.core.data.source.local.entity.ProductionCompanyEntity
import elok.dicoding.made.core.data.source.local.entity.relation.MovieWithGenres
import elok.dicoding.made.core.data.source.local.entity.relation.MovieWithProductionCompanies
import elok.dicoding.made.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalMovieDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    fun getMovieList(): Flow<List<MovieEntity>> = movieDao.getMovieList()
    fun getMovieDetail(movieId: Long): Flow<MovieEntity> = movieDao.getMovieDetail(movieId)
    fun getMovieWithGenreList(movieId: Long): Flow<MovieWithGenres> =
        movieDao.getMoviesWithGenres(movieId)

    fun getMovieWithProductionCompanyList(movieId: Long): Flow<MovieWithProductionCompanies> =
        movieDao.getMoviesWithProductionCompanies(movieId)

    suspend fun insertMovieList(movie: List<MovieEntity>) = movieDao.insertMovieList(movie)
    suspend fun insertGenreMovieList(genreMovie: List<GenreMovieEntity>) =
        movieDao.insertGenreMovieList(genreMovie)

    suspend fun insertProductionCompanyList(productionCompanies: List<ProductionCompanyEntity>) =
        movieDao.insertProductionCompanyList(productionCompanies)

    suspend fun updateMovieDetail(movie: MovieEntity) {
        movieDao.updateMovie(movie)
    }
}