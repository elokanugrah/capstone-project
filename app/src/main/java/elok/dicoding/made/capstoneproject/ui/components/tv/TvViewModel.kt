package elok.dicoding.made.capstoneproject.ui.components.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import elok.dicoding.made.capstoneproject.core.domain.model.MovieTv
import elok.dicoding.made.capstoneproject.core.domain.usecase.MovieTvUseCase
import javax.inject.Inject

class TvViewModel @Inject constructor(private val useCase: MovieTvUseCase?) : ViewModel() {
  val tvShows = useCase?.getTvShows()?.asLiveData()
  fun isFavorite(movieTv: MovieTv) = useCase?.isFavorite(movieTv)?.asLiveData()
  fun setToFavorite(movieTv: MovieTv, state: Boolean) = useCase?.setFavoriteMovieTv(movieTv, state)
}