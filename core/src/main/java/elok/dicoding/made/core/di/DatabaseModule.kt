package elok.dicoding.made.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import elok.dicoding.made.core.data.source.local.room.*
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MovieTvDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            MovieTvDatabase::class.java, "MovieTvLocal.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMovieTvDao(database: MovieTvDatabase): MovieTvDao = database.movieTvDao()

    @Provides
    fun provideFavoriteMovieTvDao(database: MovieTvDatabase): FavoriteMovieTvDao =
        database.favoriteMovieTvDao()

    @Provides
    fun provideMovieDao(database: MovieTvDatabase): MovieDao = database.movieDao()

    @Provides
    fun provideTvDao(database: MovieTvDatabase): TvDao = database.tvDao()
}