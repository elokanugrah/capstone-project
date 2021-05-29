package elok.dicoding.made.capstoneproject.ui.components.moviedetail

import androidx.lifecycle.*
import elok.dicoding.made.core.domain.model.Movie
import elok.dicoding.made.core.domain.usecase.movie.MovieUseCase
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase?
) : ViewModel() {
    private val _movieItem = MutableLiveData<Movie>()

    val movieItem: LiveData<Movie> = _movieItem
    val movieDetail = Transformations.switchMap(_movieItem) { item ->
        movieUseCase?.getMovieDetails(item)?.asLiveData()
    }
    val movieGenres = Transformations.switchMap(_movieItem) { item ->
        movieUseCase?.getMovieGenres(item)?.asLiveData()
    }
    val movieProductionCompanies = Transformations.switchMap(_movieItem) { item ->
        movieUseCase?.getMovieProductionCompanies(item)?.asLiveData()
    }

    fun setSelectedItem(item: Movie) {
        _movieItem.postValue(item)
    }
}