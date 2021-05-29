package elok.dicoding.made.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_country")
data class ProductionCountryEntity(
    @PrimaryKey
    @NonNull
    val iso31661: String,
    val movieId: Long,
    val name: String?
)
