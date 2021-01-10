package elok.dicoding.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieTvResponse(
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("popularity")
    val popularity: Float?,

    @field:SerializedName("video")
    val video: Boolean?,

    @field:SerializedName("vote_count")
    val vote_count: Int?,

    @field:SerializedName("vote_average")
    val vote_average: Float?,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("release_date")
    val release_date: String?,

    @field:SerializedName("first_air_date")
    val first_air_date: String?,

    @field:SerializedName("original_language")
    val original_language: String?,

    @field:SerializedName("original_title")
    val original_title: String?,

    @field:SerializedName("backdrop_path")
    val backdrop_path: String?,

    @field:SerializedName("adult")
    val adult: Boolean?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("poster_path")
    val poster_path: String?
)