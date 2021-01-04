package elok.dicoding.made.capstoneproject.ui.components.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.domain.usecase.MovieTvUseCase
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val useCase: MovieTvUseCase?) : ViewModel() {

  val movies = useCase?.getMovies()?.asLiveData()

  val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
  val search = queryChannel.asFlow()
    .debounce(1000)
    .distinctUntilChanged()
    .filter {
      it.trim().isNotEmpty()
    }
    .mapLatest {
      useCase?.searchMovies(it)
    }
    .asLiveData()

  fun isFavorite(movieTv: MovieTv) = useCase?.isFavorite(movieTv)?.asLiveData()
  fun setToFavorite(movieTv: MovieTv, saved: Boolean) = useCase?.setFavoriteMovieTv(movieTv, saved)
}