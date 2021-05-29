package elok.dicoding.made.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spoken_language")
data class SpokenLanguageEntity(
    @PrimaryKey
    @NonNull
    val iso6391: String,
    val movieId: Long,
    val englishName: String?,
    val name: String?
)
