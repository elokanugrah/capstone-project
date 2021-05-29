package elok.dicoding.made.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_movie")
data class GenreMovieEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Long = 0L,
    val genreMovieId: Long,
    val movieId: Long,
    val name: String?
)