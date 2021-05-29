package elok.dicoding.made.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class GenreTvResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String?
)