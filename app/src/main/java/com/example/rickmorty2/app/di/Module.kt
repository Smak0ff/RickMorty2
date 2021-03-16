package com.example.rickmorty2.app.di

import com.example.rickmorty2.retrofit.RetrofitInterface
import com.example.rickmorty2.ui.fragment.MainFragment
import com.example.rickmorty2.model.repository.CharactersRepository
import com.example.rickmorty2.viewmodel.MainFragmentViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    fragment { MainFragment() }
}

val viewModelModule = module {
    single { MainFragmentViewModel(get()) }
}

val apiModule = module {
    fun provideApiInterface(retrofit: Retrofit): RetrofitInterface{
        return retrofit.create(RetrofitInterface::class.java)
    }
    single { provideApiInterface(get()) }
}

val networkModule = module {
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder().build()
    }

    fun provideGsonConverter(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    fun provideRetrofit(gson: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit{
            return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(okHttpClient)
            .addConverterFactory(gson)
            .build()
    }
    single { provideOkHttpClient() }
    single { provideGsonConverter() }
    single { provideRetrofit(get(), get()) }
}


val repositoryModule = module {
    fun provideCharactersRepository(api: RetrofitInterface): CharactersRepository {
        return CharactersRepository(api)
    }
    single { provideCharactersRepository(get()) }
}