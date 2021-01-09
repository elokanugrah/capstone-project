package elok.dicoding.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieTvResponse(
    @field:SerializedName("results")
    val results: List<MovieTvResponse>?
)