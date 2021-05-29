package elok.dicoding.made.core.utils

import elok.dicoding.made.core.data.source.local.entity.*
import elok.dicoding.made.core.data.source.local.entity.relation.MovieWithGenres
import elok.dicoding.made.core.data.source.local.entity.relation.MovieWithProductionCompanies
import elok.dicoding.made.core.data.source.local.entity.relation.TvGenreCrossRef
import elok.dicoding.made.core.data.source.remote.response.GenreTvResponse
import elok.dicoding.made.core.data.source.remote.response.MovieResponse
import elok.dicoding.made.core.data.source.remote.response.MovieTvResponse
import elok.dicoding.made.core.data.source.remote.response.TvResponse
import elok.dicoding.made.core.domain.model.*

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

    fun mapDomainToFavoriteEntity(input: MovieTv) = FavoriteMovieTvEntity(
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

    fun mapMovieResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                budget = it.budget,
                homepage = it.homepage,
                imdbId = it.imdbId,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                revenue = it.revenue,
                runtime = it.runtime,
                status = it.status,
                tagline = it.tagline,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.movieId,
                adult = it.adult,
                budget = it.budget,
                backdropPath = it.backdropPath,
                homepage = it.homepage,
                imdbId = it.imdbId,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                revenue = it.revenue,
                runtime = it.runtime,
                status = it.status,
                tagline = it.tagline,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }

    fun mapMovieEntityToDomain(input: MovieEntity) = Movie(
        id = input.movieId,
        adult = input.adult,
        budget = input.budget,
        backdropPath = input.backdropPath,
        homepage = input.homepage,
        imdbId = input.imdbId,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        revenue = input.revenue,
        runtime = input.runtime,
        status = input.status,
        tagline = input.tagline,
        title = input.title,
        video = input.video,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount
    )

    fun mapMovieResponseToEntity(input: MovieResponse) = MovieEntity(
        movieId = input.id,
        adult = input.adult,
        budget = input.budget,
        backdropPath = input.backdropPath,
        homepage = input.homepage,
        imdbId = input.imdbId,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        revenue = input.revenue,
        runtime = input.runtime,
        status = input.status,
        tagline = input.tagline,
        title = input.title,
        video = input.video,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount
    )

    fun mapProductionCompanyResponseToEntities(input: MovieResponse): List<ProductionCompanyEntity> {
        val productionCompanyList = ArrayList<ProductionCompanyEntity>()
        input.productionCompanies?.forEach {
            val productionCompany = ProductionCompanyEntity(
                productionCompanyId = it.id,
                movieId = input.id,
                logoPath = it.logoPath,
                name = it.name,
                originCountry = it.originCountry
            )
            productionCompanyList.add(productionCompany)
        }
        return productionCompanyList
    }

    fun mapMovieGenreResponseToEntities(input: MovieResponse): List<GenreMovieEntity> {
        val movieGenreList = ArrayList<GenreMovieEntity>()
        input.genres?.forEach {
            val movieGenre = GenreMovieEntity(
                genreMovieId = it.id,
                movieId = input.id,
                name = it.name
            )
            movieGenreList.add(movieGenre)
        }
        return movieGenreList
    }

    fun mapTvResponseToEntities(input: List<TvResponse>): List<TvEntity> {
        val tvList = ArrayList<TvEntity>()
        input.map {
            val tv = TvEntity(
                tvId = it.id,
                backdropPath = it.backdropPath,
                firstAirDate = it.firstAirDate,
                name = it.name,
                originalLanguage = it.originalLanguage,
                originalName = it.overview,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
            tvList.add(tv)
        }
        return tvList
    }

    fun mapTvEntitiesToDomain(input: List<TvEntity>): List<Tv> =
        input.map {
            Tv(
                id = it.tvId,
                backdropPath = it.backdropPath,
                firstAirDate = it.firstAirDate,
                name = it.name,
                originalLanguage = it.originalLanguage,
                originalName = it.overview,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }

    fun mapGenreTvResponseToEntities(input: List<GenreTvResponse>): List<GenreTvEntity> {
        val genreTvList = ArrayList<GenreTvEntity>()
        input.map {
            val genreTv = GenreTvEntity(
                genreTvId = it.id,
                name = it.name
            )
            genreTvList.add(genreTv)
        }
        return genreTvList
    }

    fun mapGenreTvEntitiesToDomain(input: List<GenreTvEntity>): List<GenreTv> =
        input.map {
            GenreTv(
                id = it.genreTvId,
                name = it.name
            )
        }

    fun mapTvGenreToCrossRef(input: List<TvResponse>): List<TvGenreCrossRef> {
        val tvGenreList = ArrayList<TvGenreCrossRef>()
        input.map { tvs ->
            tvs.genreIds.forEach {
                val tvGenre = TvGenreCrossRef(
                    tvId = tvs.id,
                    genreTvId = it
                )
                tvGenreList.add(tvGenre)
            }
        }
        return tvGenreList
    }

    fun mapMovieWithGenreEntityToDomain(input: MovieWithGenres): List<GenreMovie> {
        val movieGenreList = ArrayList<GenreMovie>()
        input.genresMovie.forEach {
            val movieGenre = GenreMovie(
                id = it.genreMovieId,
                name = it.name
            )
            movieGenreList.add(movieGenre)
        }
        return movieGenreList
    }

    fun mapMovieWithProductionCompanyEntityToDomain(input: MovieWithProductionCompanies): List<ProducationCompany> {
        val productionCompanyList = ArrayList<ProducationCompany>()
        input.productionCompanies.forEach {
            val producationCompany = ProducationCompany(
                id = it.productionCompanyId,
                logoPath = it.logoPath,
                name = it.name,
                originCountry = it.originCountry
            )
            productionCompanyList.add(producationCompany)
        }
        return productionCompanyList
    }
}