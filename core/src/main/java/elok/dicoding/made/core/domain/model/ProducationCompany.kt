package elok.dicoding.made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProducationCompany(
    val id: Long,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?
) : Parcelable