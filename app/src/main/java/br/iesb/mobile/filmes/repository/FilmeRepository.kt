package br.iesb.mobile.filmes.repository

import br.iesb.mobile.filmes.FilmeService
import br.iesb.mobile.filmes.domain.Key.ApiKey
import br.iesb.mobile.filmes.domain.filmes.Genres
import br.iesb.mobile.filmes.domain.filmes.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmeRepository @Inject constructor(
    private val service: FilmeService,
    private val key: ApiKey
){
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