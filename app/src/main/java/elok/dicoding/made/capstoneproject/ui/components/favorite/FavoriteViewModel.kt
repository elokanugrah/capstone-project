package elok.dicoding.made.capstoneproject.ui.components.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import elok.dicoding.made.capstoneproject.core.domain.model.MovieTv
import elok.dicoding.made.capstoneproject.core.domain.usecase.MovieTvUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val useCase: MovieTvUseCase?) : ViewModel() {
    val favoriteMovies = useCase?.getFavoriteMovies()
    val favoriteTvShows = useCase?.getFavoriteTvShows()

    fun isFavorite(movieTv: MovieTv) = useCase?.isFavorite(movieTv)?.asLiveData()
    fun setToFavorite(movieTv: MovieTv, saved: Boolean) = useCase?.setFavoriteMovieTv(movieTv, saved)
}