package elok.dicoding.made.capstoneproject.ui.components.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import elok.dicoding.made.core.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase?) : ViewModel() {

    val movieList = movieUseCase?.getMovieList()?.asLiveData()

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val search = queryChannel.asFlow()
        .debounce(1000)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            movieUseCase?.searchMovieList(it)
        }
        .asLiveData()
}