package elok.dicoding.made.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ListGenreTvResponse(
    @SerializedName("genres")
    val genres: List<GenreTvResponse>?
)