package br.iesb.mobile.filmes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.filmes.domain.filmes.Genres
import br.iesb.mobile.filmes.domain.filmes.Review
import br.iesb.mobile.filmes.interactor.FilmeInterector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import br.iesb.mobile.filmes.FilmeService
import br.iesb.mobile.filmes.domain.Key.ApiKey

@HiltViewModel
class ListfilmeViewModel @Inject constructor(
    val app: Application,
    private val interactor: FilmeInterector,
    private val service: FilmeService,
    private val key: ApiKey
) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    val review = MutableLiveData<Review>()

    val genresFilms = MutableLiveData<List<Genres>>()


    fun reviewFilme(id: Int){
        launch {
            try {
                val reviewInteractor = interactor.reviewFilms(id)
                review.value = reviewInteractor.get(0)
            }catch (t: Throwable){
                Log.d("Store", "Error to List Filme: ${t.localizedMessage}" )
            }
        }
    }

    fun genresFilms(id: Int){

        launch {
            try {
                val genresInteractor = interactor.genresFilms(id)
                genresFilms.value = genresInteractor
            }catch (t: Throwable){
                Log.d("Store", "Error to List Filme: ${t.localizedMessage}" )
            }
        }
    }

}