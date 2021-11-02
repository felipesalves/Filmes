package br.iesb.mobile.filmes.repository.dto



data class FilmesAllDTO (
        val results: List<FilmesAllDetalheDTO>? = listOf()
) {
    data class FilmesAllDetalheDTO (
        val adult: Boolean,
        val backdrop_path: String? = null,
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String? = null,
        val original_title: String? = null,
        val overview:  String? = null,
        val popularity: Double,
        val poster_path: String? = null,
        val release_date: String? = null,
        var title: String? = null,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
        )
}