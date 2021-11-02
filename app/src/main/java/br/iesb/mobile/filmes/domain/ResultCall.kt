package br.iesb.mobile.filmes.domain

sealed class ResultCall <out T: Any>{
    data class Success<out T: Any>(val value: T? = null): ResultCall<T>()
    data class Error(val message: String?, val error: Throwable?): ResultCall<Nothing>()
}