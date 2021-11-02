package br.iesb.mobile.filmes.repository.dataSource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.iesb.mobile.filmes.FilmeService
import br.iesb.mobile.filmes.domain.Key.ApiKey
import br.iesb.mobile.filmes.domain.filmes.Poster

class PosterDataSource(private val service: FilmeService) :
    PagingSource<Int, Poster>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Poster> {
        val pageNumber = params.key ?: 1
        val key = ApiKey()
        return try {
            val pagedResponse = service.getPosterAsync(key.API_KEY,pageNumber)
            val data = pagedResponse?.results

            var nextPageNumber: Int? = null

                val nextPageQuery = pagedResponse.page + 1
                nextPageNumber = nextPageQuery

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Poster>): Int = 1
}