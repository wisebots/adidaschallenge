package com.bolddevoteam.adidaschallenge.fitgoals.di

import androidx.room.Room
import com.bolddevoteam.adidaschallenge.core.commons.BASE_URL
import com.bolddevoteam.adidaschallenge.core.commons.GOALS_TABLE_NAME
import com.bolddevoteam.adidaschallenge.core.commons.TAG
import com.bolddevoteam.adidaschallenge.core.extensions.logDebug
import com.bolddevoteam.adidaschallenge.core.repository.Connectivity
import com.bolddevoteam.adidaschallenge.core.repository.ConnectivityImpl
import com.bolddevoteam.adidaschallenge.fitgoals.model.local.GoalsDatabase
import com.bolddevoteam.adidaschallenge.fitgoals.model.provider.GoalsProvider
import com.bolddevoteam.adidaschallenge.fitgoals.model.provider.GoalsProviderImpl
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.GoalsApi
import com.bolddevoteam.adidaschallenge.fitgoals.model.repository.GoalsRepository
import com.bolddevoteam.adidaschallenge.fitgoals.model.repository.GoalsRepositoryImpl
import com.bolddevoteam.adidaschallenge.viewmodel.GoalsViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GoalsModule {
    fun init() = loadKoinModules(presentationModule + networkingModule + repositoryModule + providerModule + databaseModule)
}

val presentationModule = module(override = true) {
    viewModel {
        GoalsViewModel(
            get()
        )
    }
}

val repositoryModule = module(override = true) {
    factory<GoalsRepository> {
        GoalsRepositoryImpl(
            get()
        )
    }
}

val networkingModule = module(override = true) {
    single { GsonConverterFactory.create() as Converter.Factory }
    single { HttpLoggingInterceptor(
        object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                logDebug(TAG, "okhttp $message")
            }
        }).setLevel(HttpLoggingInterceptor.Level.BASIC) as Interceptor
    }
    single {
        OkHttpClient.Builder().apply {
            callTimeout(12, TimeUnit.SECONDS)
            addInterceptor(get() as Interceptor)
        }.build()
    }
    single(qualifier = named("goals")) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
    }
    single { get<Retrofit>(named("goals")).create(GoalsApi::class.java) }
}

val databaseModule = module(override = true) {
    single<GoalsDatabase>(named("goals_db")) {
        Room.databaseBuilder(androidContext(), GoalsDatabase::class.java, GOALS_TABLE_NAME).fallbackToDestructiveMigration().build()
    }
    factory { get<GoalsDatabase>(named("goals_db")).searchGoalsDao() }
}

val providerModule = module(override = true) {
    factory<GoalsProvider> { GoalsProviderImpl(get(), get()) }
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
}