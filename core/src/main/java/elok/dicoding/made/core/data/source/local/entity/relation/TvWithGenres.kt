package elok.dicoding.made.core.data.source.local.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import elok.dicoding.made.core.data.source.local.entity.GenreTvEntity
import elok.dicoding.made.core.data.source.local.entity.TvEntity

data class TvWithGenres(
    @Embedded val tv: TvEntity,
    @Relation(
        parentColumn = "tvId",
        entityColumn = "genreTvId",
        associateBy = Junction(TvGenreCrossRef::class)
    )
    val genresTv: List<GenreTvEntity>
)
