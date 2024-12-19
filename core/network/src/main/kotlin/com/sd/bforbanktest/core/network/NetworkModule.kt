package com.sd.bforbanktest.core.network

import com.sd.bforbank.core.environment.EnvironmentGateway
import com.sd.bforbanktest.core.network.resultadapter.ResultCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    internal fun httpLoggingInterceptor(environment: EnvironmentGateway) =
        HttpLoggingInterceptor().setLevel(
            if (environment.isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )

    @Provides
    internal fun moshiBuilder(): Moshi.Builder = Moshi.Builder()

    @Provides
    internal fun moshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    internal fun httpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    internal fun retrofitBuilder(
        httpClient: OkHttpClient,
        resultCallAdapterFactory: ResultCallAdapterFactory,
        api: Api,
    ): Retrofit.Builder =
        Retrofit.Builder().client(httpClient).addCallAdapterFactory(resultCallAdapterFactory)
            .baseUrl(api())

    private companion object {
        private const val CONNECTION_TIMEOUT = 40L
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 30L
    }
}
