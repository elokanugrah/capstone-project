package elok.dicoding.made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreMovie(
    val id: Long,
    val name: String?
) : Parcelable