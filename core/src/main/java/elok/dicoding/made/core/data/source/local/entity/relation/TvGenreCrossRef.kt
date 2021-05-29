package elok.dicoding.made.core.data.source.local.entity.relation

import androidx.room.Entity

@Entity(primaryKeys = ["tvId", "genreTvId"])
data class TvGenreCrossRef(
    val tvId: Long,
    val genreTvId: Long
)
