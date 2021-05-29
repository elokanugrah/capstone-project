package elok.dicoding.made.core.data.source.local.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import elok.dicoding.made.core.data.source.local.entity.MovieEntity
import elok.dicoding.made.core.data.source.local.entity.SpokenLanguageEntity

data class MovieWithSpokenLanguages(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    )
    val spokenLanguages: List<SpokenLanguageEntity>
)