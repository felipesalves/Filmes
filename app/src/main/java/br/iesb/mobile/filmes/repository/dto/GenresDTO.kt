package br.iesb.mobile.filmes.repository.dto

data class GenresDTO (
    val backdrop_path: String? = null,
    val genres: List<GenresDetailDTO>
        )