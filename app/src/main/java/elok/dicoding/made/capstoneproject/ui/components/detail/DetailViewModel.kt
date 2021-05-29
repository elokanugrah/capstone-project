package elok.dicoding.made.capstoneproject.ui.components.detail

import androidx.lifecycle.*
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.domain.model.Tv
import elok.dicoding.made.core.domain.usecase.MovieTvUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val useCase: MovieTvUseCase
) : ViewModel() {
    private val _movieTvItem = MutableLiveData<MovieTv>()
    private val _tvItem = MutableLiveData<Tv>()

    val movieTvItem: LiveData<MovieTv> = _movieTvItem
    val tvItem: LiveData<Tv> = _tvItem
    val tvGenres = Transformations.switchMap(_tvItem) { item ->
        useCase.getTvGenres(item).asLiveData()
    }
    /*var isFavorite: LiveData<Boolean> = Transformations.switchMap(_movieTvItem) { item ->
        useCase?.isFavorite(item)?.asLiveData()
    }*/

    fun setSelectedItem(item: MovieTv) {
        _movieTvItem.postValue(item)
    }

    fun setSelectedItem(item: Tv) {
        _tvItem.postValue(item)
    }

    /*fun setToFavorite(saved: Boolean) {
        _movieTvItem.value?.let {
            useCase?.setFavoriteMovieTv(it, saved)
            _movieTvItem.postValue(it)
        }
    }*/
}