package br.iesb.mobile.filmes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.iesb.mobile.filmes.domain.ResultCall
import br.iesb.mobile.filmes.interactor.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    app: Application,
    private val interactor: LoginInteractor
) : AndroidViewModel(app) {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordRepeat = MutableLiveData<String>()
    val result = MutableLiveData<ResultCall<Nothing>>()


    fun loginAccount() {
        viewModelScope.launch {
            result.value = interactor.loginAccount(email.value, password.value)
        }
    }

    fun signUpAccount() {
        viewModelScope.launch {
            result.value = interactor.signUpAccount(email.value, password.value, passwordRepeat.value)
        }
    }

    fun forgotAccount() {
        viewModelScope.launch {
            result.value = interactor.forgotAccount(email.value)
        }
    }

}