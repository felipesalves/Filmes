package br.iesb.mobile.filmes.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.iesb.mobile.filmes.FilmeService
import br.iesb.mobile.filmes.domain.filmes.Poster
import br.iesb.mobile.filmes.repository.dataSource.PosterDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PosterRepository @Inject constructor(
    private val service: FilmeService,
){

    suspend fun getAllPoster(): Flow<PagingData<Poster>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { PosterDataSource(service) }
    ).flow

}