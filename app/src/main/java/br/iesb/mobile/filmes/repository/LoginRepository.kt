package br.iesb.mobile.filmes.repository

import android.content.Context
import br.iesb.mobile.filmes.R
import br.iesb.mobile.filmes.domain.ResultCall
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepository @Inject constructor(
    private val auth: FirebaseAuth,
    @ApplicationContext val context: Context
) {

    private fun ErrorResult(e: Throwable?): ResultCall.Error {
        return when (e) {
            is FirebaseAuthInvalidCredentialsException -> ResultCall.Error(context.getString(R.string.login_invalid), e)
            is FirebaseAuthInvalidUserException -> {
                val msg = when (e.errorCode) {
                    "ERROR_USER_DISABLED" -> context.getString(R.string.login_disable)
                    "ERROR_USER_NOT_FOUND" -> context.getString(R.string.login_not_found)
                    else -> context.getString(R.string.login_invalid)
                }
                ResultCall.Error(msg, e)
            }
            else -> ResultCall.Error(context.getString(R.string.login_error), e)
        }
    }

    suspend fun loginAccount(email: String, pass:String): ResultCall<Nothing> = suspendCoroutine { nextStep ->
        val operation = auth.signInWithEmailAndPassword(email, pass)
        operation.addOnCompleteListener { op ->
            val res = if (op.isSuccessful) {
                ResultCall.Success()
            } else {
                ErrorResult(op.exception)
            }
            nextStep.resume(res)
        }
    }

    suspend fun signUpAccount(email: String, pass:String): ResultCall<Nothing> = suspendCoroutine { nextStep ->
        val operation = auth.createUserWithEmailAndPassword(email, pass)
        operation.addOnCompleteListener { op ->
            val res = if (op.isSuccessful) {
                ResultCall.Success()
            } else {
                ErrorResult(op.exception)
            }
            nextStep.resume(res)
        }
    }

    suspend fun forgotAccount(email: String): ResultCall<Nothing> = suspendCoroutine { nextStep ->
        val operation = auth.sendPasswordResetEmail(email)
        operation.addOnCompleteListener { op ->
            val res = if (op.isSuccessful) {
                ResultCall.Success()
            } else {
                ErrorResult(op.exception)
            }
            nextStep.resume(res)
        }
    }

}