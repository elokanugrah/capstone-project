package elok.dicoding.made.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv")
data class TvEntity(
    @PrimaryKey
    @NonNull
    val tvId: Long,
    val backdropPath: String?,
    val firstAirDate: String?,
    val name: String?,
    val originalLanguage: String?,
    val originalName: String?,
    val overview: String?,
    val popularity: Float?,
    val posterPath: String?,
    val voteAverage: Float?,
    val voteCount: Int?
)