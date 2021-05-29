package elok.dicoding.made.core.data.source.local.room

import androidx.room.*
import elok.dicoding.made.core.data.source.local.entity.GenreTvEntity
import elok.dicoding.made.core.data.source.local.entity.TvEntity
import elok.dicoding.made.core.data.source.local.entity.relation.GenreWithTvs
import elok.dicoding.made.core.data.source.local.entity.relation.TvGenreCrossRef
import elok.dicoding.made.core.data.source.local.entity.relation.TvWithGenres
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvList(tv: List<TvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreTvList(tv: List<GenreTvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvGenreCrossRef(crossRef: List<TvGenreCrossRef>)

    @Transaction
    @Query("SELECT * FROM tv")
    fun getTvList(): Flow<List<TvEntity>>

    @Transaction
    @Query("SELECT * FROM genre_tv")
    fun getGenreTvList(): Flow<List<GenreTvEntity>>

    @Transaction
    @Query("SELECT * FROM tv WHERE tvid = :tvId")
    fun getTvsWithGenres(tvId: Long): Flow<TvWithGenres>

    @Transaction
    @Query("SELECT * FROM genre_tv")
    suspend fun getGenresWithTvs(): List<GenreWithTvs>
}