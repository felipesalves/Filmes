package br.iesb.mobile.filmes

import br.iesb.mobile.filmes.domain.filmes.FilmesLancamentos
import br.iesb.mobile.filmes.domain.Key.ApiKey
import br.iesb.mobile.filmes.domain.filmes.Poster
import br.iesb.mobile.filmes.domain.filmes.PosterReponse
import br.iesb.mobile.filmes.repository.dto.FilmesAllDTO
import br.iesb.mobile.filmes.repository.dto.FilmesLancamentosDTO
import br.iesb.mobile.filmes.repository.dto.GenresDTO
import br.iesb.mobile.filmes.repository.dto.ReviewDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmeService {

    val key: ApiKey

    @GET("3/movie/popular")
    @Headers("Content-Type: application/json")
    suspend fun getPopularFilmsAsync(@Query("api_key") api_key: String): FilmesLancamentosDTO


    @GET("3/movie/top_rated")
    @Headers("Content-Type: application/json")
    suspend fun getAllFilmsAsync(@Query("api_key") api_key: String,
                                 @Query("page") page: Int): FilmesLancamentosDTO

    @GET("3/movie/{id}/reviews")
    @Headers("Content-Type: application/json")
    suspend fun getReviewAsync(@Path("id") int: Int, @Query("api_key") api_key: String): ReviewDTO

    @GET("3/movie/{id}")
    @Headers("Content-Type: application/json")
    suspend fun getGenresAsync(@Path("id") int: Int, @Query("api_key") api_key: String): GenresDTO

    @GET("3/movie/top_rated")
    @Headers("Content-Type: application/json")
    suspend fun getAllFilmsPaginationAsync(@Query("api_key") api_key: String,
                                 @Query("page") page: Int): FilmesAllDTO


    @GET("3/movie/top_rated")
    @Headers("Content-Type: application/json")
    suspend fun getPosterAsync(@Query("api_key") api_key: String,
                                           @Query("page") page: Int): PosterReponse<Poster>
}