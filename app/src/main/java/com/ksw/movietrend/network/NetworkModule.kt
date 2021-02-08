package com.ksw.movietrend.network

import com.ksw.movietrend.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by KSW on 2021-01-28
 */

const val LANGUAGE = "ko-KR"

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideTmdbApikeyInterceptor(@Named("tmdb_api_key") apiKey: String): Interceptor {
        return Interceptor.invoke { chain ->
            val originRequest = chain.request()
            val originUrl = originRequest.url

            val newUrl =originUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("language", LANGUAGE)
                .build()

            val newRequest = originRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Singleton
    @Provides
    fun provideCallFactory(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tmdbApikeyInterceptor: Interceptor
    ): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(tmdbApikeyInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    @Named("tmdb_base_url")
    fun provideBaseUrl(): String {
        return BuildConfig.TMDB_BASE_URL
    }

    @Singleton
    @Provides
    @Named("tmdb_api_key")
    fun provideTmdbApikey(): String {
        return BuildConfig.TMDB_API_KEY
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        httpLoggingInterceptor: Call.Factory,
        moshiConverterFactory: MoshiConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        @Named("tmdb_base_url") baseUrl : String
    ): Retrofit {
        return Retrofit.Builder()
            .callFactory(httpLoggingInterceptor)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .baseUrl(baseUrl)
            .build()

    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit) : MovieServiceApi {
        return retrofit.create(MovieServiceApi::class.java)
    }

}