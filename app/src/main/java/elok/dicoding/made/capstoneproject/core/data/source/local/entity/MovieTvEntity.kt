package elok.dicoding.made.capstoneproject.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_tv")
data class MovieTvEntity(
        @PrimaryKey
        @NonNull
        var id: Int,
        var title: String?,
        val name: String?,
        var overview: String?,
        var popularity: Float?,
        var poster_path: String?,
        var backdrop_path: String?,
        var vote_average: Float?,
        var release_date: String?,
        val first_air_date: String?
)