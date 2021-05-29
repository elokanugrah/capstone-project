package elok.dicoding.made.core.data.source.local.room

import androidx.room.*
import elok.dicoding.made.core.data.source.local.entity.GenreMovieEntity
import elok.dicoding.made.core.data.source.local.entity.MovieEntity
import elok.dicoding.made.core.data.source.local.entity.ProductionCompanyEntity
import elok.dicoding.made.core.data.source.local.entity.relation.MovieWithGenres
import elok.dicoding.made.core.data.source.local.entity.relation.MovieWithProductionCompanies
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreMovieList(movie: List<GenreMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductionCompanyList(movie: List<ProductionCompanyEntity>)

    @Transaction
    @Query("SELECT * FROM movie")
    fun getMovieList(): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    fun getMovieDetail(movieId: Long): Flow<MovieEntity>

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    fun getMoviesWithGenres(movieId: Long): Flow<MovieWithGenres>

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    fun getMoviesWithProductionCompanies(movieId: Long): Flow<MovieWithProductionCompanies>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movie: MovieEntity)
}