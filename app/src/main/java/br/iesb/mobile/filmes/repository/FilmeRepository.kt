package br.iesb.mobile.filmes.repository

import androidx.paging.PagingSource
import br.iesb.mobile.filmes.FilmeService
import br.iesb.mobile.filmes.domain.filmes.FilmesLancamentos
import br.iesb.mobile.filmes.domain.Key.ApiKey
import br.iesb.mobile.filmes.domain.filmes.FilmesAlls
import br.iesb.mobile.filmes.domain.filmes.Genres
import br.iesb.mobile.filmes.domain.filmes.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmeRepository @Inject constructor(
    private val service: FilmeService,
    private val key: ApiKey
){
    suspend fun listPopularFilms(): List<FilmesLancamentos>{
        return withContext(Dispatchers.IO){
            val responseService =  service.getPopularFilmsAsync(key.API_KEY)

            val response = responseService.results.map {
                FilmesLancamentos(
                        adult = it.adult,
                        backdrop_path = it.backdrop_path,
                        genre_ids = it.genre_ids,
                        id = it.id,
                        original_language = it.original_language,
                        original_title = it.original_title,
                        overview = it.overview,
                        popularity = it.popularity,
                        poster_path = it.poster_path,
                        release_date = it.release_date,
                        title = it.title,
                        video = it.video,
                        vote_average = it.vote_average,
                        vote_count = it.vote_count
                )
            }
            response
        }
    }

    suspend fun listAllFilms(page: Int): List<FilmesAlls>{
        return withContext(Dispatchers.IO){
            val responseService =  service.getAllFilmsAsync(key.API_KEY, page)

            val response = responseService.results.map {
                FilmesAlls(
                        adult = it.adult,
                        backdrop_path = it.backdrop_path,
                        genre_ids = it.genre_ids,
                        id = it.id,
                        original_language = it.original_language,
                        original_title = it.original_title,
                        overview = it.overview,
                        popularity = it.popularity,
                        poster_path = it.poster_path,
                        release_date = it.release_date,
                        title = it.title,
                        video = it.video,
                        vote_average = it.vote_average.toString(),
                        vote_count = it.vote_count
                )
            }
            response
        }
    }

    suspend fun reviewFilms(id: Int): List<Review>{
        return withContext(Dispatchers.IO){
            val responseService =  service.getReviewAsync(id, key.API_KEY)

            val response = responseService.results.map {
                Review(
                    author = it.author,
                    content = it.content
                )
            }
            response
        }
    }

    suspend fun genresFilms(id: Int): List<Genres>{
        return withContext(Dispatchers.IO){
            val responseService =  service.getGenresAsync(id, key.API_KEY)

            val response = responseService.genres.map {
                Genres(
                    id = it.id,
                    name = it.name
                )
            }
            response
        }
    }


}