package elok.dicoding.made.core.domain.usecase.movie

import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.domain.model.GenreMovie
import elok.dicoding.made.core.domain.model.Movie
import elok.dicoding.made.core.domain.model.ProducationCompany
import elok.dicoding.made.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getMovieList(): Flow<Resource<List<Movie>>> = movieRepository.getMovieList()

    override fun searchMovieList(query: String): Flow<Resource<List<Movie>>> =
        movieRepository.searchMovieList(query)

    override fun getMovieDetails(movie: Movie): Flow<Resource<Movie>> =
        movieRepository.getMovieDetails(movie)

    override fun getMovieGenres(movie: Movie): Flow<List<GenreMovie>> =
        movieRepository.getMovieGenres(movie)

    override fun getMovieProductionCompanies(movie: Movie): Flow<List<ProducationCompany>> =
        movieRepository.getMovieProductionCompanies(movie)
}