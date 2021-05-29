package elok.dicoding.made.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.data.source.local.entity.relation.TvWithGenres
import elok.dicoding.made.core.domain.model.GenreTv
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.domain.model.Tv
import elok.dicoding.made.core.domain.repository.IMovieTvRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieTvInteractor @Inject constructor(private val movieTvRepository: IMovieTvRepository) :
    MovieTvUseCase {

    override fun getMovies(): Flow<Resource<List<MovieTv>>> = movieTvRepository.getMovies()

    override fun getTvShows(): Flow<Resource<List<MovieTv>>> = movieTvRepository.getTvShows()

    override fun getFavoriteMovies(): LiveData<PagedList<MovieTv>> =
        movieTvRepository.getFavoriteMovies()

    override fun getFavoriteTvShows(): LiveData<PagedList<MovieTv>> =
        movieTvRepository.getFavoriteTvShows()

    override fun searchMovies(query: String): Flow<Resource<List<MovieTv>>> =
        movieTvRepository.searchMovies(query)

    override fun searchTvShows(query: String): Flow<Resource<List<MovieTv>>> =
        movieTvRepository.searchTvShows(query)

    override fun setFavoriteMovieTv(movieTv: MovieTv, saved: Boolean) =
        movieTvRepository.setFavoriteMovieTv(movieTv, saved)

    override fun isFavorite(movieTv: MovieTv) = movieTvRepository.isFavorite(movieTv)

    override fun getTvList(): Flow<Resource<List<Tv>>> = movieTvRepository.getTvList()

    override fun getGenreTvList(): Flow<Resource<List<GenreTv>>> =
        movieTvRepository.getGenreTvList()

    override fun getTvGenres(tv: Tv): Flow<TvWithGenres> = movieTvRepository.getTvGenres(tv)

    override fun searchTvList(query: String): Flow<Resource<List<Tv>>> =
        movieTvRepository.searchTvList(query)
}