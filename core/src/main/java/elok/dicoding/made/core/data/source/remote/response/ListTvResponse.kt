package elok.dicoding.made.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ListTvResponse(
    @SerializedName("results")
    val results: List<TvResponse>?
)