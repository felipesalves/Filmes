package br.iesb.mobile.filmes.interactor

import android.app.Application
import android.util.Patterns
import br.iesb.mobile.filmes.R
import br.iesb.mobile.filmes.domain.ResultCall
import br.iesb.mobile.filmes.repository.LoginRepository
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val repo: LoginRepository,
    private val app: Application,
){

    private fun validateData(
        email: String?,
        password: String?,
        type: String?,
        passwordRepeat: String?,
    ): Pair<Pair<String, String>?, Throwable?> {

        if (email.isNullOrBlank()) return Pair(null, Exception(app.getString(R.string.email_required)))

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Pair(null, Exception(app.getString(R.string.invalid_email)))
        }


        if (password.isNullOrBlank()) return Pair(null, Exception(app.getString(R.string.password_required)))


        if (password.length < 6) {
            return Pair(null, Exception(app.getString(R.string.password_minimum_length)))
        }


        if(type.equals("singUp")){
            if (passwordRepeat != null) {
                if (passwordRepeat.length < 6) {
                    return Pair(null, Exception(app.getString(R.string.password_minimum_length)))
                }
            }

            if(!password.equals(passwordRepeat)){
                return Pair(null, Exception(app.getString(R.string.password_different)))
            }
        }

        return Pair(Pair(email, password), null)
    }


    suspend fun loginAccount(email: String?, password: String?): ResultCall<Nothing> {
        val (credential, ex) = validateData(email, password, "login", "")
        return when (ex) {
            null -> repo.loginAccount(credential!!.first, credential.second)
            else -> ResultCall.Error(ex.localizedMessage, ex)
        }
    }

    suspend fun signUpAccount(email: String?, password: String?, passwordRepeat: String?): ResultCall<Nothing> {
        val (credential, ex) = validateData(email, password, "singUp", passwordRepeat )

        return when (ex) {
            null -> repo.signUpAccount(credential!!.first, credential.second)
            else -> ResultCall.Error(ex.localizedMessage, ex)
        }
    }

    suspend fun forgotAccount(email: String?): ResultCall<Nothing> {

        val (credential, ex) = validateData(email, "######", "forgot", "" )

        return when (ex) {
            null -> repo.forgotAccount(credential!!.first)
            else -> ResultCall.Error(ex.localizedMessage, ex)
        }

    }

}