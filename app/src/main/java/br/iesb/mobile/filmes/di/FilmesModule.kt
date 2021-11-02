package br.iesb.mobile.filmes.di

import br.iesb.mobile.filmes.FilmeService
import br.iesb.mobile.filmes.domain.Key.ApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class FilmesModule {

    @Provides
    fun providerRetrofit(): Retrofit {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(" https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun filmeService(retrofit: Retrofit): FilmeService =
        retrofit.create(FilmeService::class.java)

    @Provides
    fun chaveAPi() = ApiKey()

}