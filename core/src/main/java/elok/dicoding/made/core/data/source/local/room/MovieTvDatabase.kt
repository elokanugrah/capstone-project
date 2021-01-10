package elok.dicoding.made.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import elok.dicoding.made.core.data.source.local.entity.FavoriteMovieTvEntity
import elok.dicoding.made.core.data.source.local.entity.MovieTvEntity

@Database(
    entities = [MovieTvEntity::class, FavoriteMovieTvEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieTvDatabase : RoomDatabase() {
    abstract fun movieTvDao(): MovieTvDao
    abstract fun favoriteMovieTvDao(): FavoriteMovieTvDao
}