package elok.dicoding.made.core.data.source.local.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import elok.dicoding.made.core.data.source.local.entity.GenreTvEntity
import elok.dicoding.made.core.data.source.local.entity.TvEntity

data class GenreWithTvs(
    @Embedded val genreTv: GenreTvEntity,
    @Relation(
        parentColumn = "genreTvId",
        entityColumn = "tvId",
        associateBy = Junction(TvGenreCrossRef::class)
    )
    val tvs: List<TvEntity>
)
