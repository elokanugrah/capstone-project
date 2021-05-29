package elok.dicoding.made.core.data.source.local.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import elok.dicoding.made.core.data.source.local.entity.GenreMovieEntity
import elok.dicoding.made.core.data.source.local.entity.MovieEntity

data class MovieWithGenres(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    )
    val genresMovie: List<GenreMovieEntity>
)
