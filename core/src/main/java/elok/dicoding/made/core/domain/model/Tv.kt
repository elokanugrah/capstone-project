package elok.dicoding.made.core.domain.model

import android.os.Parcelable
import elok.dicoding.made.core.utils.ext.changeDateFormat
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tv(
    val id: Long,
    val backdropPath: String?,
    val firstAirDate: String?,
    val name: String?,
    val originalLanguage: String?,
    val originalName: String?,
    val overview: String?,
    val popularity: Float?,
    val posterPath: String?,
    val voteAverage: Float?,
    val voteCount: Int?
) : Parcelable {
    fun getRatingAverage() = voteAverage?.div(2) ?: 0f
    fun getDateFormatted() = firstAirDate?.changeDateFormat("yyyy-MM-dd", "dd MMM yyyy")
}
