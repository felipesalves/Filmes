package br.iesb.mobile.filmes.domain.filmes



data class PosterReponse<T> (
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
    val results: List<T> = listOf()
        )

