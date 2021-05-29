package elok.dicoding.made.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_tv")
data class GenreTvEntity(
    @PrimaryKey
    @NonNull
    val genreTvId: Long,
    val name: String?
)