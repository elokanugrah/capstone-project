package elok.dicoding.made.capstoneproject.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieTvResponse(
    @field:SerializedName("page")
    val page: Int?,

    @field:SerializedName("total_results")
    val total_results: Int?,

    @field:SerializedName("total_pages")
    val total_pages: Int?,

    @field:SerializedName("results")
    val results: List<MovieTvResponse>?
)