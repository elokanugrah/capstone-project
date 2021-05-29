package elok.dicoding.made.core.domain.repository

import elok.dicoding.made.core.data.Resource
import elok.dicoding.made.core.domain.model.GenreMovie
import elok.dicoding.made.core.domain.model.Movie
import elok.dicoding.made.core.domain.model.ProducationCompany
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovieList(): Flow<Resource<List<Movie>>>
    fun searchMovieList(query: String): Flow<Resource<List<Movie>>>
    fun getMovieDetails(movie: Movie): Flow<Resource<Movie>>
    fun getMovieGenres(movie: Movie): Flow<List<GenreMovie>>
    fun getMovieProductionCompanies(movie: Movie): Flow<List<ProducationCompany>>
}