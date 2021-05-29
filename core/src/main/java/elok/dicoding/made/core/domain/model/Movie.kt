package elok.dicoding.made.core.domain.model

import android.os.Parcelable
import elok.dicoding.made.core.utils.ext.changeDateFormat
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val adult: Boolean?,
    val budget: Long?,
    val backdropPath: String?,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Float?,
    val posterPath: String?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Float?,
    val voteCount: Int?
) : Parcelable {
    fun getRatingAverage() = voteAverage?.div(2) ?: 0f
    fun getDateFormatted() = releaseDate?.changeDateFormat("yyyy-MM-dd", "dd MMM yyyy")
    fun getYearFormatted() = releaseDate?.changeDateFormat("yyyy-MM-dd", "yyyy")
}