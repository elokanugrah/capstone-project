package elok.dicoding.made.capstoneproject.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import elok.dicoding.made.capstoneproject.core.data.source.local.entity.MovieTvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieTvDao {
    //Movie item has title
    @Query("select * from movie_tv where title != ''")
    fun getMovies(): Flow<List<MovieTvEntity>>

    //Tv show item has name
    @Query("select * from movie_tv where name != ''")
    fun getTvShows(): Flow<List<MovieTvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTv(movie: List<MovieTvEntity>)
}