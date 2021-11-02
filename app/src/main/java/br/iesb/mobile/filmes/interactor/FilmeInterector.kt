package br.iesb.mobile.filmes.interactor

import br.iesb.mobile.filmes.domain.filmes.Genres
import br.iesb.mobile.filmes.domain.filmes.Review
import br.iesb.mobile.filmes.repository.FilmeRepository
import javax.inject.Inject

class FilmeInterector @Inject constructor(
    private val repo: FilmeRepository
){
    suspend fun reviewFilms(id: Int): List<Review>{
        return repo.reviewFilms(id)
    }

    suspend fun genresFilms(id: Int): List<Genres>{
        return repo.genresFilms(id)
    }

}