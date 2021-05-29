package elok.dicoding.made.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import elok.dicoding.made.core.data.source.local.entity.*
import elok.dicoding.made.core.data.source.local.entity.relation.TvGenreCrossRef

@Database(
    entities = [
        MovieTvEntity::class,
        FavoriteMovieTvEntity::class,
        MovieEntity::class,
        GenreMovieEntity::class,
        ProductionCompanyEntity::class,
        ProductionCountryEntity::class,
        SpokenLanguageEntity::class,
        TvEntity::class,
        GenreTvEntity::class,
        TvGenreCrossRef::class],
    version = 2,
    exportSchema = false
)
abstract class MovieTvDatabase : RoomDatabase() {
    abstract fun movieTvDao(): MovieTvDao
    abstract fun favoriteMovieTvDao(): FavoriteMovieTvDao
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}