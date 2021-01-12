package elok.dicoding.made.favorites.ui

import androidx.lifecycle.ViewModel
import elok.dicoding.made.core.domain.usecase.MovieTvUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(useCase: MovieTvUseCase?) : ViewModel() {
    val favoriteMovies = useCase?.getFavoriteMovies()
    val favoriteTvShows = useCase?.getFavoriteTvShows()
}