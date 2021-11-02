package br.iesb.mobile.filmes.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.iesb.mobile.filmes.base.BaseViewModel
import br.iesb.mobile.filmes.domain.filmes.Poster
import br.iesb.mobile.filmes.repository.PosterRepository
import kotlinx.coroutines.flow.Flow

class PosterViewModel  @ViewModelInject constructor(
    private val repository: PosterRepository
)   : BaseViewModel() {
    private lateinit var _posterFlow: Flow<PagingData<Poster>>
    val PosterFragment: Flow<PagingData<Poster>>
        get() = _posterFlow

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() = launchPagingAsync({
        repository.getAllPoster().cachedIn(viewModelScope)
    }, {
        _posterFlow = it
    })
}