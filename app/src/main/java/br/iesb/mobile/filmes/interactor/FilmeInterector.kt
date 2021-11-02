package br.iesb.mobile.filmes.interactor

import br.iesb.mobile.filmes.domain.filmes.FilmesAlls
import br.iesb.mobile.filmes.domain.filmes.FilmesLancamentos
import br.iesb.mobile.filmes.domain.filmes.Genres
import br.iesb.mobile.filmes.domain.filmes.Review
import br.iesb.mobile.filmes.repository.FilmeRepository
import javax.inject.Inject

class FilmeInterector @Inject constructor(
    private val repo: FilmeRepository
){
    suspend fun listPopularFilms(): List<FilmesLancamentos>{
        return repo.listPopularFilms()
    }

    suspend fun listAllFilms(page: Int): List<FilmesAlls>{
        return repo.listAllFilms(page)
    }

    suspend fun reviewFilms(id: Int): List<Review>{
        return repo.reviewFilms(id)
    }

    suspend fun genresFilms(id: Int): List<Genres>{
        return repo.genresFilms(id)
    }


}