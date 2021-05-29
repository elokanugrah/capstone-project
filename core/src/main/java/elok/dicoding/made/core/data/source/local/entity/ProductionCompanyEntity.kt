package elok.dicoding.made.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_company")
data class ProductionCompanyEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Long = 0L,
    val productionCompanyId: Long,
    val movieId: Long,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?
)
