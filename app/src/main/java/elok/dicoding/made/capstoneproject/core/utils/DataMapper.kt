package elok.dicoding.made.capstoneproject.core.utils

import androidx.lifecycle.LiveData
import elok.dicoding.made.capstoneproject.core.data.source.local.entity.FavoriteMovieTvEntity
import elok.dicoding.made.capstoneproject.core.data.source.local.entity.MovieTvEntity
import elok.dicoding.made.capstoneproject.core.data.source.remote.response.MovieTvResponse
import elok.dicoding.made.capstoneproject.core.domain.model.MovieTv

object DataMapper {

    fun mapResponseToEntities(input: List<MovieTvResponse>): List<MovieTvEntity> {
        val movieList = ArrayList<MovieTvEntity>()
        input.map {
            val movie = MovieTvEntity(
                id = it.id ?: 0,
                title = it.title,
                name = it.name,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                vote_average = it.vote_average,
                release_date = it.release_date,
                first_air_date = it.first_air_date
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieTvEntity>): List<MovieTv> =
        input.map {
            MovieTv(
                id = it.id,
                title = it.title,
                name = it.name,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                vote_average = it.vote_average,
                release_date = it.release_date,
                first_air_date = it.first_air_date
            )
        }

    fun mapEntityToDomain(input: MovieTvEntity) = MovieTv(
        id = input.id,
        title = input.title,
        name = input.name,
        overview = input.overview,
        popularity = input.popularity,
        poster_path = input.poster_path,
        backdrop_path = input.backdrop_path,
        vote_average = input.vote_average,
        release_date = input.release_date,
        first_air_date = input.first_air_date
    )

    fun mapFavoriteEntityToDomain(input: FavoriteMovieTvEntity) = MovieTv(
            id = input.id,
            title = input.title,
            name = input.name,
            overview = input.overview,
            popularity = input.popularity,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            vote_average = input.vote_average,
            release_date = input.release_date,
            first_air_date = input.first_air_date
    )

    fun mapDomainToEntity(input: MovieTv) = MovieTvEntity(
        id = input.id ?: 0,
        title = input.title,
        name = input.name,
        overview = input.overview,
        popularity = input.popularity,
        poster_path = input.poster_path,
        backdrop_path = input.backdrop_path,
        vote_average = input.vote_average,
        release_date = input.release_date,
        first_air_date = input.first_air_date
    )

    fun mapDomainToFavoriteEntity(input: MovieTv) = FavoriteMovieTvEntity(
            id = input.id ?: 0,
            title = input.title,
            name = input.name,
            overview = input.overview,
            popularity = input.popularity,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            vote_average = input.vote_average,
            release_date = input.release_date,
            first_air_date = input.first_air_date
    )
}